# IVYPORTAL-20137 — Portal JSON Export/Import - Concept for Implementation

### Export
- Export all non-empty JSON files separately, packaged as `Portal_Package.zip`
- Filenames: `Portal_Dashboard.json`, `Portal_CustomStatistic.json`, `Portal_UserMenu.json`, `Portal_CaseDetails.json`, `Portal_ThirdPartyApplications.json`, `Portal_MenuOrder.json`, `Portal_ExternalLinks.json`
- JSON structure of each file follows existing structure

### Import
- Upload `Portal_Package.zip` file
- Replace all existing data for each variable
- File name should follow the pattern Portal_[type name].json
- Show preview information before importing

### Notes
- Upload `Portal_Package.zip` file
- Replace all existing data for each variable
- File name should follow the pattern Portal_[type name].json
- Show preview information before importing


