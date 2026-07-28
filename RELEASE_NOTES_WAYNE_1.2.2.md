# Life Dashboard Companion 1.2.2-wayne.4

Personal Wayne build for reliable Screen Time and Health Connect catch-up sync.

## Fixed in 1.2.2-wayne.4

- Manual uploads now run in an application-owned coroutine, so switching between
  the Screen Time and Health Connect tabs no longer cancels an in-flight upload.
- Heart-rate samples are reduced to one average sample per minute before JSON
  serialisation, preventing multi-megabyte catch-up payloads such as the
  116,265-record upload observed on 26 July 2026.
- Webhook read/write timeout increased from 10 to 30 seconds to tolerate mobile
  network and reverse-proxy latency without premature retries.
- This APK is versionCode 8 and signed with Wayne's personal release key, so it
  updates the prior `1.2.2-wayne.3` personal build in place.

## Fixed in 1.2.2-wayne.3

- Screen Time sync now includes foreground app sessions with start time, end
  time, duration, app/package name, device, and timezone context.
- Mission Control can use these sessions for hourly screen-time visualisation
  and nightly pattern commentary.
- This APK is versionCode 7 and signed with Wayne's personal release key, so it
  updates the prior `1.2.2-wayne.2` personal build in place.

## Fixed in 1.2.2-wayne.1 / 1.2.2-wayne.2

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
- Step, distance, active calorie, and total calorie exports now include Health
  Connect source package/app metadata when Health Connect exposes it.
- Steps, distance, active calories, and total calories now resend full local-day
  windows instead of partial "since last sync" totals, so daily dashboards can
  replace the stored day value safely.

## Install Notes

This build keeps the upstream Android package name:
`com.owen282000.lifedashboard`.

Because it is signed with Wayne's personal signing key rather than the upstream
release key, Android will not install it over the original GitHub release. To
use it, uninstall the existing Life Dashboard app first, then install this APK
and grant Health Connect permissions again.

Before uninstalling, save your webhook URL and any custom headers.

Download APK:
[`downloads/life-dashboard-companion-1.2.2-wayne.4-release.apk`](downloads/life-dashboard-companion-1.2.2-wayne.4-release.apk)

APK SHA-256:
`5d16f0ba3dd5342fba4d5cdd52879a19b28c5ba547c8089e9012afef534c1fad`

## Attribution

Original project:
[`owen282000/life-dashboard-companion-app`](https://github.com/owen282000/life-dashboard-companion-app)

Original project license: MIT, preserved in this fork.
