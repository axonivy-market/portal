# -*- coding: utf-8 -*-

def getCurrentYear():
    from datetime import datetime
    return str(datetime.today().year)

def parseVersionFromPom():
    from xml.etree import ElementTree as et
    tree = et.ElementTree()
    tree.parse('../pom.xml')
    ns = {"mvn":"http://maven.apache.org/POM/4.0.0"}
    version = tree.getroot().find('./mvn:parent/mvn:version', ns).text
    if version.endswith('-SNAPSHOT'):
        version = version[:-9]
    return version

# project
project = 'Axon.ivy Digital Business Platform'
copyright = getCurrentYear() + ', AXON Ivy AG'
version = parseVersionFromPom()
release = version

# general options
needs_sphinx = '1.5.6'
master_doc = 'index'
pygments_style = 'tango'
add_function_parentheses = True

extensions = [
    'sphinx.ext.extlinks',
    'sphinxprettysearchresults'
]

templates_path = ['_templates']
exclude_trees = []
source_suffix = ['.rst']
source_encoding = 'utf-8-sig'

# html options
html_theme = 'sphinx_rtd_theme'
html_use_index = True
html_show_sourcelink = False
html_static_path = ['_static']
html_logo = '_static/images/axonivylogo.png'
html_theme_options = {
    'logo_only': True
}
html_favicon = '_static/images/favicon.png'

# base urls
# https://stackoverflow.com/questions/1227037/substitutions-inside-links-in-rest-sphinx
extlinks = {
    'dev-url':  ('https://developer.axonivy.com%s', None),
    'public-api':  ('https://developer.axonivy.com/doc/latest/PublicAPI%s', None)
}
