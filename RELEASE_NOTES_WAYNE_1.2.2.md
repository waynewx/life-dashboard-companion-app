# Life Dashboard Companion 1.2.2-wayne.1

Personal fix build for Health Connect Steps/Distance export issues seen on
transferred devices or partial permission states.

## Fixed

- Data type rows now reflect the exact Health Connect read permission for that
  data type.
- Sync, Preview, and Export now request any missing permissions for the selected
  data types before reading Health Connect.
- Health Connect raw record reads now follow every `readRecords()` page instead
  of only using the first page. This addresses stale high-volume data such as
  Heart Rate.
- Steps and Distance are read from Health Connect aggregate totals over local-day
  windows, matching what Health Connect shows more closely than raw record reads.
- Incremental filters now use records strictly after the previous sync timestamp
  to avoid duplicate boundary records.
- Health Connect webhook payloads now report the installed app version instead
  of the hardcoded upstream `1.2.1` value.

## Install Notes

This build keeps the upstream Android package name:
`com.owen282000.lifedashboard`.

Because it is signed with Wayne's personal signing key rather than the upstream
release key, Android will not install it over the original GitHub release. To
use it, uninstall the existing Life Dashboard app first, then install this APK
and grant Health Connect permissions again.

Before uninstalling, save your webhook URL and any custom headers.

Download archive:
[`downloads/life-dashboard-companion-1.2.2-wayne.1-release.zip`](downloads/life-dashboard-companion-1.2.2-wayne.1-release.zip)

SHA-256:
`7612317317cb902f7202248a090cb28f7c4e9b48e7c577a79c2f54c146b91490`

## Attribution

Original project:
[`owen282000/life-dashboard-companion-app`](https://github.com/owen282000/life-dashboard-companion-app)

Original project license: MIT, preserved in this fork.
