# Axon.ivy Product Documentation

Based on [sphinx](http://www.sphinx-doc.org).
Using reStructredText as markup language.


## Build

	# start docker-compose
	docker-compose up

You can browse the documentation at http://127.0.0.1:8000


## Dev Environment Setup

We recommend to use Eclipse or Visual Studio Code.

* Eclipse
  * Plugin RST

* Visual Studio Code
  * Plugin lextudio.restructuredtext for syntax highlighting and code completion.
  * Plugin rewrap to wrap lines to 80 characters with ALT+Q


## References

* http://www.sphinx-doc.org/en/master/usage/restructuredtext/index.html
* https://www.sphinx-doc.org/en/master/index.html
* https://sphinx-rtd-theme.readthedocs.io/en/latest/demo/demo.html
* https://raw.githubusercontent.com/rtfd/sphinx_rtd_theme/master/docs/demo/demo.rst
* https://rest-sphinx-memo.readthedocs.io/en/latest/ReST.html#tables


## Guidelines

- GUI elements
  :guilabel:`Next` button'

- Files and Directories
  :file:`docker-entrypoint.sh`
  :file:`[engineDir]/configuration/defaults`

- Inline code
  :code:`docker run -p 8080:8080 axonivy/axonivy-engine:dev`

- Inline code block
  .. code-block:: bash

    docker run -p 8080:8080 axonivy/axonivy-engine

- Include code files
  .. literalinclude:: includes/docker-entrypoint.sh
    :language: bash
    :linenos:

- Links to dev.axonivy.com
  :dev-url:`Download </download>`

- Links to public api
  :public-api:`IvyPrimefacesThemeResolver </ch/ivyteam/ivy/jsf/primefaces/theme/IvyPrimefacesThemeResolver.html>`

- Warnings
  .. Warn:: Attention! Make a backup!

- Tips
  .. Tip:: Best Practice. Advice!

- External Link
  `Title <https://www.google.ch>`_ 

- Internal link (original title)
  :ref:`web-xml`

- Interanl Link (custom title)
  :ref:`prepared <deployment-prepare>`

- Image
  .. figure:: images/workflow-demos.png
