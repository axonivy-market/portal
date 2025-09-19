#!/bin/bash

version="$1"            # 10.0.0 etc
major="${version%%.*}"  # 10

# switch to directory of script
pushd $(dirname "${BASH_SOURCE[0]}") 2>&1 1> /dev/null

# check all RST files for start of placeholders, and feed them to sed
# execute sed only on files containing placeholders.
replace_expr="s/\\|ivy-designer\\|/Axon Ivy Designer/g; \
s/\\|ivy-engine\\|/Axon Ivy Engine/g; \
s/\\|ivy\\|/Axon Ivy/g; \
s/\\|version\\|/${version}/g; \
s/\\|major-version\\|/${major}/g;"

echo "[substitute] Applying placeholder replacement to .rst sources..."
find source -name "*.rst" -exec grep -IlE "\\|(ivy|version|major-version)\\|" {} \; | while read -r rst; do
  sed -i -E "${replace_expr}" "$rst"
done

# Also update .po files so msgid matches modified sources
PO_BASE="weblate/locale"
if [ -d "${PO_BASE}" ]; then
  echo "[substitute] Applying placeholder replacement to .po translation files..."
  find "${PO_BASE}" -name "*.po" | while read -r po; do
    # Apply only if placeholders still present
    if grep -qE "\\|(ivy|version|major-version)\\|" "$po"; then
      sed -i -E "${replace_expr}" "$po"
    fi
  done
else
  echo "[substitute] No translation directory '${PO_BASE}' found. Skipping .po updates."
fi

popd 2>&1 1> /dev/null