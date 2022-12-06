#!/bin/bash

version="$1"            # 10.0.0 etc
major="${version%%.*}"  # 10

# switch to directory of script
pushd $(dirname "${BASH_SOURCE[0]}") 2>&1 1> /dev/null

cd source/

# check all RST files for start of placeholders, and feed them to sed
# execute sed only on files containing placeholders.
for name in $(find . -name "*.rst" -exec grep -lE "\|(ivy|version|major)" {} \;) ; do
    sed -i -E "s/\|ivy-designer\|/Axon Ivy Designer/g; \
            s/\|ivy-engine\|/Axon Ivy Engine/g; \
            s/\|ivy\|/Axon Ivy/g; \
            s/\|version\|/$version/g; \
            s/\|major-version\|/$major/g;" $name
done

popd 2>&1 1> /dev/null