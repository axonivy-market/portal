#!/bin/bash

# ============================================================
# Database Setup Script
# This script automates database backup, creation, and restore
# ============================================================

# ------------------------------------------------------------
# USAGE
# ------------------------------------------------------------
usage() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  --source-db           Source database name (default: AxonIvySystemDatabase-performance-master)"
    echo "  --target-db           Target database name (default: AxonIvySystemDatabase-performance-manual-testing)"
    echo "  --backup-dir          Backup directory path (default: /var/database-backups)"
    echo "  --source-project-dir  Source project directory (default: /var/tools/ivy/performance-master)"
    echo "  --project-name        Project name (default: performance-manual-testing)"
    echo "  --engine-image        Docker engine image name (default: axonivy/axonivy-engine:nightly)"
    echo "  --container-name      Docker container name (default: ivy-performance-manual-testing)"
    echo "  --host-port           Host port for the container (default: 9001)"
    echo "  --help                Show this help message"
    echo ""
    echo "Example:"
    echo "  $0 --source-db AxonIvySystemDatabase-performance-12 --target-db my-test-db --host-port 9002"
    exit 0
}

# ------------------------------------------------------------
# PARSE COMMAND LINE ARGUMENTS
# ------------------------------------------------------------

# Default values
SOURCE_DB="AxonIvySystemDatabase-performance-master"
TARGET_DB="AxonIvySystemDatabase-performance-manual-testing"
BACKUP_DIR="/var/database-backups"
PROJECT_BASE_DIR="/var/tools/ivy"
SOURCE_PROJECT_DIR="${PROJECT_BASE_DIR}/performance-master"
PROJECT_NAME="performance-manual-testing"
ENGINE_IMAGE_NAME="axonivy/axonivy-engine:nightly"
CONTAINER_NAME="ivy-performance-manual-testing"
HOST_PORT="9001"

# Parse arguments
while [[ $# -gt 0 ]]; do
    case $1 in
        --source-db)
            SOURCE_DB="$2"
            shift 2
            ;;
        --target-db)
            TARGET_DB="$2"
            shift 2
            ;;
        --backup-dir)
            BACKUP_DIR="$2"
            shift 2
            ;;
        --source-project-dir)
            SOURCE_PROJECT_DIR="$2"
            shift 2
            ;;
        --project-name)
            PROJECT_NAME="$2"
            shift 2
            ;;
        --engine-image)
            ENGINE_IMAGE_NAME="$2"
            shift 2
            ;;
        --container-name)
            CONTAINER_NAME="$2"
            shift 2
            ;;
        --host-port)
            HOST_PORT="$2"
            shift 2
            ;;
        --help)
            usage
            ;;
        *)
            echo "Unknown option: $1"
            usage
            ;;
    esac
done

# ------------------------------------------------------------
# DERIVED CONFIGURATION
# ------------------------------------------------------------
TIMESTAMP=$(date +"%d-%m-%Y_%H-%M")
BACKUP_FILENAME="${SOURCE_DB}-${TIMESTAMP}.dump"
BACKUP_FILE_PATH="${BACKUP_DIR}/${BACKUP_FILENAME}"

# All cloned projects are placed under performance-manual-testing folder
PROJECT_DIR="${PROJECT_BASE_DIR}/performance-manual-testing-all/${PROJECT_NAME}"

CONTAINER_PORT="8080"

SYSTEM_DB_URL="jdbc:postgresql://postgres_all:5432/${TARGET_DB}"

# ------------------------------------------------------------
# MAIN EXECUTION
# ------------------------------------------------------------

main() {
    echo "============================================================"
    echo "STAGE 1: DATABASE BACKUP, AND RESTORE TO NEW DATABASE"
    echo ""
    echo "Configuration:"
    echo "  - Source Database : ${SOURCE_DB}"
    echo "  - Target Database : ${TARGET_DB}"
    echo "  - Backup Directory: ${BACKUP_DIR}"
    echo "  - Backup Filename : ${BACKUP_FILENAME}"
    echo "============================================================"
    echo ""

    backup_database
    create_database
    restore_database
    
    echo ""
    echo "============================================================"
    echo "Database setup completed successfully!"
    echo "============================================================"
    echo ""
    
    echo "============================================================"
    echo "STAGE 2: PROJECT DIRECTORY AND DOCKER COMPOSE SETUP"
    echo ""
    echo "Configuration:"
    echo "  - Project Directory: ${PROJECT_DIR}"
    echo "  - Container Name   : ${CONTAINER_NAME}"
    echo "  - Port Mapping     : ${HOST_PORT}:${CONTAINER_PORT}"
    echo "============================================================"
    echo ""
    
    create_project_directory
    create_configuration_directory
    copy_configuration_files
    modify_database_url_in_ivy_yaml
    copy_deployed_applications
    create_docker_compose
    start_docker_compose
    print_cleanup_command
}

# ------------------------------------------------------------
# FUNCTIONS
# ------------------------------------------------------------

# Color codes
BLUE='\033[0;34m'
GREEN='\033[0;32m'
RED='\033[0;31m'
YELLOW='\033[0;33m'
NC='\033[0m' # No Color

# Function to print colored messages
print_message() {
    # $1 = message type (INFO, SUCCESS, ERROR, STEP)
    # $2 = message text
    case $1 in
        "INFO")
            echo -e "${BLUE}[INFO]${NC} $2"
            ;;
        "SUCCESS")
            echo -e "${GREEN}[SUCCESS]${NC} $2"
            ;;
        "ERROR")
            echo -e "${RED}[ERROR]${NC} $2"
            ;;
        "STEP")
            echo -e "\n${YELLOW}==>${NC} $2"
            ;;
    esac
}

backup_database() {
    print_message "INFO" "Starting backup of database: ${SOURCE_DB}"
    print_message "INFO" "Backup file will be saved to: ${BACKUP_FILE_PATH}"
    
    chmod -R 777 "${BACKUP_DIR}"

    # pg_dump command explanation:
    # -u postgres  : Run as postgres user
    # -Fc          : Output in custom format (compressed, suitable for pg_restore)
    docker exec -u postgres postgres pg_dump -Fc "${SOURCE_DB}" > "${BACKUP_FILE_PATH}"
    
    # Check if backup was successful
    # $? contains the exit code of the last command (0 = success)
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Database backup completed successfully!"
    else
        print_message "ERROR" "Database backup failed!"
        exit 1
    fi
}

create_database() {
    print_message "INFO" "Creating new database: ${TARGET_DB}"
    
    # psql -c : Execute a single SQL command
    # Note: Database names with hyphens need to be quoted
    docker exec -u postgres postgres psql -c "CREATE DATABASE \"${TARGET_DB}\";"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Database '${TARGET_DB}' created successfully!"
    else
        print_message "ERROR" "Failed to create database!"
        exit 1
    fi
}

restore_database() {
    print_message "INFO" "Restoring backup to database: ${TARGET_DB}"
    print_message "INFO" "Using backup file: ${BACKUP_FILE_PATH}"
    
    # pg_restore command explanation:
    # -d          : Target database name
    # -Fc         : Input is in custom format
    # -i          : Keep stdin open (needed for piping data)
    docker exec -i -u postgres postgres pg_restore -d "${TARGET_DB}" -Fc < "${BACKUP_FILE_PATH}"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Database restored successfully!"
    else
        print_message "ERROR" "Database restore failed!"
        cleanup_database
        exit 1
    fi
}

cleanup_database() {
    print_message "INFO" "Cleaning up: Dropping database ${TARGET_DB}"
    
    docker exec -u postgres postgres psql -c "DROP DATABASE IF EXISTS \"${TARGET_DB}\";"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Database ${TARGET_DB} dropped successfully."
    else
        print_message "ERROR" "Failed to drop database ${TARGET_DB}. Manual cleanup may be required."
    fi
}

create_project_directory() {
    print_message "INFO" "Creating directory: ${PROJECT_DIR}"
    
    if [ -d "${PROJECT_DIR}" ]; then
        print_message "ERROR" "Directory ${PROJECT_DIR} already exists!"
        echo "Please remove it first or choose a different project name."
        exit 1
    fi
    
    mkdir -p "${PROJECT_DIR}"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Project directory created successfully!"
    else
        print_message "ERROR" "Failed to create project directory!"
        exit 1
    fi
}

create_configuration_directory() {
    print_message "INFO" "Creating configuration directory: ${PROJECT_DIR}/configuration"
    
    mkdir -p "${PROJECT_DIR}/configuration"
    mkdir -p "${PROJECT_DIR}/applications"
    mkdir -p "${PROJECT_DIR}/data"
    mkdir -p "${PROJECT_DIR}/work"
    mkdir -p "${PROJECT_DIR}/logs"

    chmod -R 777 "${PROJECT_DIR}"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Configuration directory created successfully!"
    else
        print_message "ERROR" "Failed to create configuration directory!"
        exit 1
    fi
}

copy_configuration_files() {
    print_message "INFO" "Copying configuration files from ${SOURCE_PROJECT_DIR}/configuration"
    
    # Array of files to copy
    local files=("ivy.yaml" "jvm.options" "licence.lic")
    
    for file in "${files[@]}"; do
        local source="${SOURCE_PROJECT_DIR}/configuration/${file}"
        local dest="${PROJECT_DIR}/configuration/${file}"
        
        if [ ! -f "${source}" ]; then
            print_message "ERROR" "Source file not found: ${source}"
            exit 1
        fi
        
        print_message "INFO" "Copying ${file}..."
        cp "${source}" "${dest}"
        
        if [ $? -eq 0 ]; then
            print_message "SUCCESS" "${file} copied successfully!"
        else
            print_message "ERROR" "Failed to copy ${file}!"
            exit 1
        fi
    done
    
    # Copy applications configurations folder (contain app.yml, variables.Portal.Dashboard.yml, etc.)
    local source_apps="${SOURCE_PROJECT_DIR}/configuration/applications"
    local dest_apps="${PROJECT_DIR}/configuration/applications"
    
    if [ -d "${source_apps}" ]; then
        print_message "INFO" "Copying applications folder..."
        cp -r "${source_apps}" "${dest_apps}"
        
        if [ $? -eq 0 ]; then
            print_message "SUCCESS" "Applications folder copied successfully!"
        else
            print_message "ERROR" "Failed to copy applications folder!"
            exit 1
        fi
    else
        print_message "ERROR" "Source applications folder not found: ${source_apps}"
        exit 1
    fi
    
    print_message "SUCCESS" "All configuration files copied successfully!"
}

modify_database_url_in_ivy_yaml() {
    print_message "INFO" "Modifying SystemDb.Url in ivy.yaml"
    
    local ivy_yaml="${PROJECT_DIR}/configuration/ivy.yaml"
    
    if [ ! -f "${ivy_yaml}" ]; then
        print_message "ERROR" "ivy.yaml not found at ${ivy_yaml}"
        exit 1
    fi
    
    # Use sed to replace the SystemDb.Url value
    # This matches lines with SystemDb: followed by Url: and replaces the URL
    sed -i "/SystemDb:/,/^[^ ]/ s|Url:.*|Url: ${SYSTEM_DB_URL}|" "${ivy_yaml}"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "SystemDb.Url updated to: ${SYSTEM_DB_URL}"
    else
        print_message "ERROR" "Failed to modify ivy.yaml!"
        exit 1
    fi
}

create_docker_compose() {
    print_message "INFO" "Creating docker-compose.yml in ${PROJECT_DIR}"
    
    cat > "${PROJECT_DIR}/docker-compose.yml" << EOF
services:
  ivy:
    image: ${ENGINE_IMAGE_NAME}
    container_name: ${CONTAINER_NAME}
    ports:
      - "${HOST_PORT}:${CONTAINER_PORT}"
    volumes:
     - ./configuration/applications/Portal:/ivy/configuration/applications/Portal
     - ./configuration/ivy.yaml:/ivy/configuration/ivy.yaml
     - ./configuration/licence.lic:/ivy/configuration/licence.lic
     - ./configuration/jvm.options:/ivy/configuration/jvm.options
     - ./configuration/elasticsearch/jvm.options:/ivy/configuration/elasticsearch/jvm.options
     - ./applications:/ivy/applications
     - ./data:/ivy/data
     - ./work:/ivy/work
     - ./logs:/ivy/logs
    networks:
     - postgres_network

networks:
  postgres_network:
    external: true
EOF
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "docker-compose.yml created successfully!"
        print_message "INFO" "File location: ${PROJECT_DIR}/docker-compose.yml"
        print_message "INFO" "Container name: ${CONTAINER_NAME}"
        print_message "INFO" "Mapped port: ${HOST_PORT}:${CONTAINER_PORT}"
    else
        print_message "ERROR" "Failed to create docker-compose.yml!"
        exit 1
    fi
}

copy_deployed_applications() {
    print_message "INFO" "Copying deployed applications from ${SOURCE_PROJECT_DIR}/applications"
    
    # Array of application paths to copy (relative to applications directory)
    local apps=(
        "Portal/portal/1.zip"
        "Portal/portal-components/1.zip"
        "Portal/PortalKitTestHelper/1.zip"
    )
    
    for app in "${apps[@]}"; do
        local source="${SOURCE_PROJECT_DIR}/applications/${app}"
        local dest="${PROJECT_DIR}/applications/${app}"
        local dest_dir=$(dirname "${dest}")
        
        if [ ! -f "${source}" ]; then
            print_message "ERROR" "Source application not found: ${source}"
            exit 1
        fi
        
        # Create destination directory if it doesn't exist
        mkdir -p "${dest_dir}"
        
        print_message "INFO" "Copying ${app}..."
        cp "${source}" "${dest}"
        
        if [ $? -eq 0 ]; then
            print_message "SUCCESS" "${app} copied successfully!"
        else
            print_message "ERROR" "Failed to copy ${app}!"
            exit 1
        fi
    done
    
    print_message "SUCCESS" "All deployed applications copied successfully!"
}

start_docker_compose() {
    print_message "INFO" "Starting Docker Compose in ${PROJECT_DIR}"
    
    cd "${PROJECT_DIR}"
    
    docker compose up -d
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Docker Compose started successfully!"
        print_message "INFO" "Container ${CONTAINER_NAME} is running on port ${HOST_PORT}"
        print_message "INFO" "You can check logs with: docker logs ${CONTAINER_NAME}"
    else
        print_message "ERROR" "Failed to start Docker Compose!"
        exit 1
    fi
}

print_cleanup_command() {
    local clean_script_dir="${PROJECT_BASE_DIR}/performance-manual-testing-all"
    
    echo ""
    echo "============================================================"
    echo "To clean up this setup later, run:"
    echo "============================================================"
    echo ""
    echo "${clean_script_dir}/clean.sh --container-name \"${CONTAINER_NAME}\" --project-dir \"${PROJECT_DIR}\" --target-db \"${TARGET_DB}\""
    echo ""
    echo "============================================================"
}

# Run the main function
main