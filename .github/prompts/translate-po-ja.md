# Translate the Portal guide PO catalogs into Japanese

You are running in `Documentation/portal-guide`. Translate the untranslated
Japanese entries in the Sphinx PO catalogs under
`weblate/locale/ja/LC_MESSAGES/`: `index.po`, `portal-components.po`,
`portal-developer-guide.po`, `portal-user-guide.po`.

## Work only on what changed

These catalogs run up to 1 MB each. **Never read one in full.** Locate the work
with the commands below, then read only the lines around each hit.

New strings — the line number of every genuinely empty `msgstr`:

```
awk '/^msgstr ""$/{n=NR; next} n && $0=="" {print n} {n=0} END{if(n) print n}' FILE
```

Use this, not a plain `grep 'msgstr ""'`. Most raw grep hits are the opening
line of a multi-line translation that is already finished — on a catalog with
one untranslated string, grep returns ten hits and this awk returns the one.

Reworded strings:

```
grep -n '^#, fuzzy' FILE
```

Such an entry keeps its old translation plus a `#| msgid` line holding the old
English. Update the wording to match the new `msgid`, then delete the
`#, fuzzy` and `#| msgid` lines.

Ignore the `#, fuzzy` on the catalog header at the top of the file — the entry
whose `msgid` is `""` is metadata. Never translate or edit it. Ignore anything
commented out with `#~`; those are obsolete.

If a file has no hits, leave it alone. If no file has hits, do nothing.

For each entry, read about 40 lines around it with `sed -n 'START,ENDp' FILE`.
The neighbouring entries are human-reviewed and set the terminology and tone —
follow them.

## reStructuredText syntax you must preserve

The msgid is reST source. Getting the markup wrong breaks the published page,
so this matters more than an elegant phrasing.

**`:ref:` has two forms with opposite rules.**

- `` :ref:`some-anchor` `` — a bare cross-reference target. Copy it verbatim.
  Never translate the text inside.
- `` :ref:`Link text <some-anchor>` `` — translate **Link text**, keep
  `` <some-anchor> `` exactly as it is, angle brackets included.

```
msgid  "See :ref:`accessibility-settings` for details."
msgstr "詳細は :ref:`accessibility-settings` をご参照ください。"

msgid  "See :ref:`Admin Settings <admin-settings>`."
msgstr ":ref:`管理者設定 <admin-settings>` をご参照ください。"
```

**`:guilabel:`** — the label is what the user sees on screen, so translate it,
*unless* it is a configuration variable, a dashboard template name or another
identifier that appears in English in the product. Config keys stay verbatim:

```
:guilabel:`Save`                      -> :guilabel:`保存`
:guilabel:`Portal.Chat.EnablePrivate` -> :guilabel:`Portal.Chat.EnablePrivate`
```

**`:download:`file.iar <path/to/file.iar>`** — copy the whole role verbatim.
Both halves are file names.

**`:kbd:`Enter`** — copy verbatim. Key names stay in English.

**` ``literal`` `** — copy verbatim. These are code, paths, URLs, `.ivp` links,
variable names.

**`|icon-name|`** — a substitution for an image. Copy verbatim. Some entries
consist of nothing but a substitution name, with or without the pipes
(`|edit-icon|`, `edit-icon`) — copy those entries unchanged.

**`**bold**`** — translate the words between the asterisks, keep the asterisks.
Bold usually marks a UI element, so render it the way the surrounding entries
render that same element.

**Placeholders** — `%s`, `%(name)s`, `{name}`, `${revision}`: copy verbatim.

Never move text into or out of a role. `` the :guilabel:`Save` button `` must
not become `` :guilabel:`」をクリックし、` ``; the role holds the label and
nothing else. Keep leading and trailing spaces as they are in the source, and
keep `\n` only where the source has it.

## Leave these in English

- Product and component names: Axon Ivy, Axon Ivy Portal, Portal, Engine,
  Designer, Cockpit, Weblate.
- Configuration variables and their prefixes: `Portal.Chat.Enabled`,
  `Portal.Document.EnablePreview`, and anything else of that shape.
- File names, paths, URLs, class and method names, `.ivp` links.
- Keyboard shortcuts: `Alt + 1`, `Ctrl + S`, `Enter`.
- Dashboard and template proper names that ship in English, such as
  `Default Template`, `Full Task List dashboard`.
- Bare identifier slugs used as substitution or image names, e.g.
  `multi-app-structure`, `portal-ajax-error-handler`.

## Glossary

Axon Ivy platform terms, from
https://developer.axonivy.com/doc/14.0/en/glossary/glossary.html and its
Japanese counterpart. Use these renderings:

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

These are platform concepts. When one of these words is clearly being used in
its ordinary English sense rather than as the Axon Ivy concept, translate it
normally.

For a term in this table the glossary wins, even if a nearby entry renders it
differently — the catalogs are not fully consistent. `Role`, for example,
appears as both ロール and 役割 today; use ロール. For UI labels that are *not*
in this table, follow the surrounding entries instead.

## Style

Neutral, instructional documentation addressed to the reader; match the
register of the surrounding entries. Table headers, single words and short UI
labels stay short — do not expand them into sentences.

## Scope

Change nothing else. Do not touch any `msgid`, do not reorder, add or delete
entries, and do not edit the file header. Do not add comments of your own.
