"""Updates Portal POM files: project/parent versions, portal dependencies, and Maven properties."""
import xml.etree.ElementTree as ET
import argparse, re, sys
from pathlib import Path

MAVEN_NS = '{http://maven.apache.org/POM/4.0.0}'

PORTAL_SCHEMA_JAVA = Path('AxonIvyPortal/portal/src/com/axonivy/portal/enums/PortalSchema.java')
PORTAL_VARIABLE_JAVA = Path('AxonIvyPortal/portal/src/ch/ivy/addon/portalkit/enums/PortalVariable.java')
VARIABLES_DIR = Path('AxonIvyPortal/portal/config/variables')
SCHEMA_ENTRY_PATTERN = re.compile(r'^\s*\w+\(\s*PortalVariable\.(\w+)\s*,', re.MULTILINE)
VARIABLE_KEY_PATTERN = re.compile(r'(\w+)\(\s*"([^"]+)"\s*\)')
SCHEMA_URL_PATTERN = re.compile(r'("\$schema": "https://axonivy-market\.github\.io/portal/schema/)[^/"]+(/)')

def update_property(file_path, property_name, new_value):
    if not new_value:
        return
    ET.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    tree = ET.parse(file_path)
    properties = tree.getroot().find(f'{MAVEN_NS}properties')
    if properties is None:
        return
    element = properties.find(f'{MAVEN_NS}{property_name}')
    if element is None:
        return
    if element.text != new_value:
        element.text = new_value
        tree.write(file_path, encoding="utf-8", xml_declaration=True)

def schema_variable_files():
    """Resolves the config file of every variable that has a PortalSchema, e.g. Portal.Dashboard -> variables/Portal/Dashboard.json."""
    schema_variables = SCHEMA_ENTRY_PATTERN.findall(PORTAL_SCHEMA_JAVA.read_text(encoding='utf-8'))
    variable_keys = dict(VARIABLE_KEY_PATTERN.findall(PORTAL_VARIABLE_JAVA.read_text(encoding='utf-8')))
    files = []
    for constant in schema_variables:
        if constant not in variable_keys:
            raise ValueError(f'PortalVariable.{constant} has no key in {PORTAL_VARIABLE_JAVA}')
        files.append(VARIABLES_DIR / (variable_keys[constant].replace('.', '/') + '.json'))
    if not files:
        raise ValueError(f'No schema variables found in {PORTAL_SCHEMA_JAVA}')
    return files

def update_schema_urls(file_path, portal_version):
    if not portal_version:
        return
    path = Path(file_path)
    if not path.exists():
        return
    content = path.read_text(encoding='utf-8')
    updated = SCHEMA_URL_PATTERN.sub(lambda m: f'{m.group(1)}{portal_version}{m.group(2)}', content)
    if updated != content:
        path.write_text(updated, encoding='utf-8')

def update_versions(file_path, portal_version=None, parent_version=None):
    if not portal_version and not parent_version:
        return
    ET.register_namespace('', 'http://maven.apache.org/POM/4.0.0')
    tree = ET.parse(file_path)
    root = tree.getroot()
    modified = False
    
    if portal_version:
        version = root.find(f'{MAVEN_NS}version')
        if version is not None and version.text != portal_version:
            version.text = portal_version
            modified = True
        
        dependencies = root.find(f'{MAVEN_NS}dependencies')
        if dependencies is not None:
            for dependency in dependencies.findall(f'{MAVEN_NS}dependency'):
                group_id = dependency.find(f'{MAVEN_NS}groupId')
                if group_id is not None and group_id.text == "com.axonivy.portal":
                    dep_version = dependency.find(f'{MAVEN_NS}version')
                    if dep_version is not None and dep_version.text != portal_version:
                        dep_version.text = portal_version
                        modified = True
    
    if parent_version:
        parent = root.find(f'{MAVEN_NS}parent')
        if parent is not None:
            parent_ver = parent.find(f'{MAVEN_NS}version')
            if parent_ver is not None and parent_ver.text != parent_version:
                parent_ver.text = parent_version
                modified = True
    
    if modified:
        tree.write(file_path, encoding="utf-8", xml_declaration=True)

def main():
    parser = argparse.ArgumentParser(description='Update Portal POM versions')
    parser.add_argument('--portal-version')
    parser.add_argument('--engine-version')
    parser.add_argument('--parent-version')
    parser.add_argument('--build-plugin-version')
    args = parser.parse_args()
    
    if not any([args.portal_version, args.engine_version, args.parent_version, args.build_plugin_version]):
        return 0
    
    root_dir = Path('.')
    
    pom_patterns = [
        'AxonIvyPortal/*/pom.xml',
        'Showcase/*/pom.xml',
        'AxonIvyPortal/portal-selenium-test/customized_pom.xml',
        'AxonIvyPortal/portal-selenium-test/document_screenshot_pom.xml',
        'AxonIvyPortal/portal-selenium-test/deployment-pom.xml',
        'Documentation/public-api/pom.xml'
    ]
    
    pom_files = []
    for pattern in pom_patterns:
        pom_files.extend(root_dir.glob(pattern))
    
    for pom_file in pom_files:
        update_property(str(pom_file), 'build.plugin.version', args.build_plugin_version)
        update_property(str(pom_file), 'ivy.engine.version', args.engine_version)
        update_versions(str(pom_file), args.portal_version, args.parent_version)
    
    update_property(str(root_dir / 'Documentation/maven/pom.xml'), 'revision', args.portal_version)

    for schema_file in schema_variable_files():
        update_schema_urls(schema_file, args.portal_version)
    
    return 0

if __name__ == '__main__':
    sys.exit(main())
