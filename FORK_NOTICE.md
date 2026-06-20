# Fork Notice

This repository is a personal fork of
[`owen282000/life-dashboard-companion-app`](https://github.com/owen282000/life-dashboard-companion-app).

The original Life Dashboard Companion app was created by Owen and is distributed
under the MIT license included in this repository. This fork preserves that
license and attribution.

## Wayne Health Connect Fix Build

This fork adds a focused fix for Health Connect syncs where transferred app
state or partial permissions can leave Steps and Distance selected in the app UI
without those exact Health Connect permissions being granted on the current
device.

Changes in `1.2.2-wayne.1` and `1.2.2-wayne.2`:

- Check Health Connect permissions per enabled data type instead of treating any
  granted Health Connect permission as sufficient.
- Request missing permissions before Sync, Preview, or Export.
- Show when selected data types still need permissions.
- Read all pages from Health Connect `readRecords()` responses so high-volume
  record types such as Heart Rate do not lag behind at the first page.
- Read Steps and Distance through Health Connect aggregate totals over local-day
  windows so exported records match the totals Health Connect shows.
- Use strict incremental filtering after the previous sync timestamp to avoid
  duplicate boundary records.
- Report the real installed app version in Health Connect webhook payloads.
- Preserve source package/app metadata for step, distance, active calorie, and
  total calorie records where Health Connect exposes it.
- Resend full local-day totals for step, distance, active calorie, and total
  calorie data so the receiving dashboard does not overwrite daily totals with
  partial post-sync fragments.

The package name is unchanged from upstream. If your installed app was signed by
the upstream release key, uninstall the old app before installing this personal
build, then re-enter webhook settings and grant permissions again.
