# Translate the Portal guide PO catalogs into Japanese

You are in `Documentation/portal-guide`. Fill in the missing Japanese
translations in the four Sphinx PO catalogs under
`weblate/locale/ja/LC_MESSAGES/`: `index.po`, `portal-components.po`,
`portal-developer-guide.po`, `portal-user-guide.po`.

They run up to 1 MB each — **never read one in full**. Use the commands below.

## Procedure

One catalog at a time.

### 1. Find the entries needing work

Empty translations:

```
awk '/^msgstr ""$/{n=NR; next} n && $0=="" {print n} {n=0} END{if(n) print n}' FILE
```

Not a plain `grep 'msgstr ""'` — most of its hits are the opening line of a
finished multi-line translation.

Fuzzy matches:

```
grep -n '^#, fuzzy' FILE
```

Skip the header entry at the top of the file (its `msgid` is `""`) and anything
commented out with `#~`. No hits in any catalog means you are done.

### 2. Read ~40 lines around each hit

```
sed -n 'START,ENDp' FILE
```

Use them to understand the context the entry sits in.

### 3. Translate it and write it back

Following the **Translation rules** below, fill in the translation to the
`msgstr`.

A fuzzy `msgstr` was copied from a similar but different string, so it is
usually wrong, roles and anchors included. Translate the `msgid` from scratch,
use the old text only as a hint for tone, and delete the `#, fuzzy` line.

Never touch a `msgid`, never reorder, add or delete entries, never edit the
header, and do not add comments of your own.

### 4. Self-check

Re-run both searches from step 1 on every catalog: the awk must print nothing,
the fuzzy grep nothing but the `index.po` header.

HEAD holds the catalogs as they were before you started, so the diff is exactly
your own work. Source strings must be untouched:

```
git diff -- weblate/locale | grep '^[-+]msgid'
```

Any output means you broke a `msgid`. Restore that file and redo it — git needs
the `./` from this subdirectory:

```
git show "HEAD:./weblate/locale/ja/LC_MESSAGES/FILE.po" > weblate/locale/ja/LC_MESSAGES/FILE.po
```

Finally read `git diff -- weblate/locale` and check every role, anchor, literal
and substitution in a `msgstr` against its `msgid`.

## Translation rules

Broken markup breaks the published page, so it outranks elegant phrasing.

**`:ref:` has two forms with opposite rules.** A bare target is copied verbatim;
in the titled form only the text is translated and `<target>` stays:

```
msgid  "See :ref:`accessibility-settings` for details."
msgstr "詳細は :ref:`accessibility-settings` をご参照ください。"

msgid  "See :ref:`Admin Settings <admin-settings>`."
msgstr ":ref:`管理者設定 <admin-settings>` をご参照ください。"
```

**`:guilabel:`** — translate the on-screen label, but not when it is a config
variable, template name or other identifier shown in English:

```
:guilabel:`Save`                      -> :guilabel:`保存`
:guilabel:`Portal.Chat.EnablePrivate` -> :guilabel:`Portal.Chat.EnablePrivate`
```

**`:download:`, `:kbd:`** — copy the whole role verbatim.

**` ``literal`` `** — copy verbatim: code, paths, URLs, `.ivp` links, variable
names, and placeholders inside them like `` ``news_{uuid}`` ``.

**`|icon-name|`** — an image substitution, copy verbatim. Entries that are
nothing but a substitution name, piped or bare (`|edit-icon|`, `edit-icon`),
are copied unchanged.

**`**bold**`** — translate between the asterisks, keep the asterisks. It
usually marks a UI element; render it as the surrounding entries do.

Never move text into or out of a role: `` the :guilabel:`Save` button `` must
not become `` :guilabel:`」をクリックし、` ``. Keep leading and trailing spaces,
and keep `\n` only where the source has it.

### Leave in English

- Product names: Axon Ivy, Axon Ivy Portal, Portal, Engine, Designer, Cockpit.
- Config variables: `Portal.Chat.Enabled`, `Portal.Document.EnablePreview`,
  and anything of that shape.
- File names, paths, URLs, class and method names, `.ivp` links.
- Keyboard shortcuts: `Alt + 1`, `Enter`.
- Names that ship in English: `Default Template`, `Full Task List dashboard`.
- Identifier slugs: `multi-app-structure`, `portal-ajax-error-handler`.

### Glossary

Axon Ivy platform terms (developer.axonivy.com/doc/14.0/en/glossary). These win
over a nearby entry that renders them differently — the catalogs are not
consistent; `Role` appears as both ロール and 役割, use ロール. For UI labels not
listed here, follow the neighbours instead.

| English | Japanese |
| --- | --- |
| Application | アプリケーション |
| Application Version | アプリケーションバージョン |
| Case | ケース |
| Connector | コネクター |
| HTML Dialog | HTML ダイアログ |
| Layout | レイアウト |
| Process | プロセス |
| Process Data | プロセスデータ |
| Process Element | プロセス要素 |
| Project | プロジェクト |
| Role | ロール |
| Signature | シグネチャ |
| Step | ステップ |
| Task | タスク |
| User | ユーザー |
| User Dialog | ユーザーダイアログ |
| View Type | ビューのタイプ |

Translate one of these normally when it is plainly the ordinary English word
rather than the Axon Ivy concept.

### Style

Neutral and instructional, matching the surrounding entries. Keep table
headers, single words and short UI labels short.
