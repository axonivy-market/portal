#!/bin/bash

# ============================================================
# Clean-up Script
# This script removes container, project folder, and database
# ============================================================

# ------------------------------------------------------------
# USAGE
# ------------------------------------------------------------
usage() {
    echo "Usage: $0 [OPTIONS]"
    echo ""
    echo "Options:"
    echo "  --container-name    Docker container name to remove (required)"
    echo "  --project-dir       Project directory to remove (required)"
    echo "  --target-db         Target database name to drop (required)"
    echo "  --help              Show this help message"
    echo ""
    echo "Example:"
    echo "  $0 --container-name ivy-performance-manual-testing --project-dir /var/tools/ivy/performance-manual-testing-all/test --target-db AxonIvySystemDatabase-performance-manual-testing"
    exit 0
}

# ------------------------------------------------------------
# PARSE COMMAND LINE ARGUMENTS
# ------------------------------------------------------------

CONTAINER_NAME=""
PROJECT_DIR=""
TARGET_DB=""

while [[ $# -gt 0 ]]; do
    case $1 in
        --container-name)
            CONTAINER_NAME="$2"
            shift 2
            ;;
        --project-dir)
            PROJECT_DIR="$2"
            shift 2
            ;;
        --target-db)
            TARGET_DB="$2"
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
# VALIDATE PARAMETERS
# ------------------------------------------------------------

if [ -z "${CONTAINER_NAME}" ]; then
    echo "Error: --container-name is required"
    usage
fi

if [ -z "${PROJECT_DIR}" ]; then
    echo "Error: --project-dir is required"
    usage
fi

if [ -z "${TARGET_DB}" ]; then
    echo "Error: --target-db is required"
    usage
fi

# ------------------------------------------------------------
# FUNCTIONS
# ------------------------------------------------------------

print_message() {
    echo "[$1] $2"
}

# ------------------------------------------------------------
# MAIN EXECUTION
# ------------------------------------------------------------

echo "============================================================"
echo "Clean-up Script"
echo ""
echo "Configuration:"
echo "  - Container Name : ${CONTAINER_NAME}"
echo "  - Project Dir    : ${PROJECT_DIR}"
echo "  - Target Database: ${TARGET_DB}"
echo "============================================================"
echo ""

# Step 1: Stop and remove the container
print_message "INFO" "Stopping and removing container: ${CONTAINER_NAME}"
docker rm -f "${CONTAINER_NAME}" 2>/dev/null

if [ $? -eq 0 ]; then
    print_message "SUCCESS" "Container ${CONTAINER_NAME} removed successfully!"
else
    print_message "WARN" "Container ${CONTAINER_NAME} not found or already removed."
fi

# Step 2: Remove the project folder
print_message "INFO" "Removing project folder: ${PROJECT_DIR}"

if [ -d "${PROJECT_DIR}" ]; then
    rm -rf "${PROJECT_DIR}"
    
    if [ $? -eq 0 ]; then
        print_message "SUCCESS" "Project folder ${PROJECT_DIR} removed successfully!"
    else
        print_message "ERROR" "Failed to remove project folder ${PROJECT_DIR}!"
        exit 1
    fi
else
    print_message "WARN" "Project folder ${PROJECT_DIR} not found or already removed."
fi

# Step 3: Remove the database
print_message "INFO" "Dropping database: ${TARGET_DB}"
docker exec -u postgres postgres psql -c "DROP DATABASE IF EXISTS \"${TARGET_DB}\" WITH (FORCE);"

if [ $? -eq 0 ]; then
    print_message "SUCCESS" "Database ${TARGET_DB} dropped successfully!"
else
    print_message "ERROR" "Failed to drop database ${TARGET_DB}!"
    exit 1
fi

echo ""
echo "============================================================"
echo "Clean-up completed successfully!"
echo "============================================================"
