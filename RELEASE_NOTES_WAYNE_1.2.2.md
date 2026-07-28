# Life Dashboard Companion 1.2.2-wayne.6

Personal Wayne build for reliable Screen Time and Health Connect catch-up sync.

## Fixed in 1.2.2-wayne.6

- Fixed the post-upload crash seen after `wayne.5` successfully sent Health
  Connect data to Mission Control.
- Exercise metadata backfill now runs once after upgrade instead of resending
  the complete seven-day session window on every sync.
- Local webhook history is bounded and raw payloads are truncated, preventing
  repeated large syncs from exhausting app memory through SharedPreferences.
- HTTP response bodies are always closed and local diagnostic logging can no
  longer fail an otherwise successful sync.
- Manual sync completion is guarded so a stale UI callback cannot terminate the
  application process.
- This APK is versionCode 10 and signed with Wayne's personal release key, so it
  updates the prior `1.2.2-wayne.5` personal build in place.

## Fixed in 1.2.2-wayne.5

- Exercise Sessions now export their Health Connect title, notes, source
  package/app, stable record IDs, distance, and active calories.
- Workout-scoped distance and active-calorie records are correlated with each
  exercise by interval overlap. Broad daily aggregates are deliberately
  excluded so a short workout cannot inherit an entire day's burn.
- The full seven-day exercise window is resent after upgrades so previously
  bare sessions can be enriched safely by the dashboard's idempotent upsert.
- This APK is versionCode 9 and signed with Wayne's personal release key, so it
  updates the prior `1.2.2-wayne.4` personal build in place.

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
[`life-dashboard-companion-1.2.2-wayne.6-release.apk`](https://github.com/waynewx/life-dashboard-companion-app/releases/download/v1.2.2-wayne.6/life-dashboard-companion-1.2.2-wayne.6-release.apk)

APK SHA-256:
`b233cf594ab9232fd3971a44b918756431eebfc96846f8601656b4fea6a34819`

## Attribution

Original project:
[`owen282000/life-dashboard-companion-app`](https://github.com/owen282000/life-dashboard-companion-app)

Original project license: MIT, preserved in this fork.
