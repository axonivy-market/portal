"""Updates Portal POM files: project/parent versions, portal dependencies, and Maven properties."""
import xml.etree.ElementTree as ET
import argparse, sys
from pathlib import Path

MAVEN_NS = '{http://maven.apache.org/POM/4.0.0}'

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
    
    return 0

if __name__ == '__main__':
    sys.exit(main())
