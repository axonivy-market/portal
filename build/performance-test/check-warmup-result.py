#!/usr/bin/env python3
"""Report whether the warm up walkthrough of the performance test came back clean.

The warm up round walks the whole scenario, so its result file tells us whether the
application works at all. Without this check an application that answers every request with
an error page is reported by perfReport as a performance regression, which reads as "the
code got slower" instead of "nothing worked".

Exits with a non zero status, and an explanation, as soon as anything is off.
"""
import collections
import csv
import sys

REQUIRED_COLUMNS = ('label', 'responseCode', 'success')


def read_samples(resultFile):
  # JMeter quotes every field that holds a comma, a transaction controller row for example
  # reads "Number of samples in transaction : 3, number of failing samples : 0", and the
  # response body kept for a failed sample spans several lines. Splitting on commas gets
  # those rows wrong, a CSV reader does not.
  with open(resultFile, newline='', encoding='utf-8', errors='replace') as content:
    reader = csv.DictReader(content)
    missing = [column for column in REQUIRED_COLUMNS if column not in (reader.fieldnames or [])]
    if missing:
      sys.exit('Cannot read %s, it has no %s column.' % (resultFile, ' and no '.join(missing)))
    return list(reader)


def describe(sample):
  status = (sample.get('responseCode') or '').strip()
  return '%s answered %s' % (sample.get('label'), 'HTTP ' + status if status else 'nothing')


def main():
  resultFile = sys.argv[1]
  samples = read_samples(resultFile)
  if not samples:
    sys.exit('Cannot read %s, it holds no sample.' % resultFile)

  failed = collections.Counter()
  unreadable = 0
  for sample in samples:
    success = (sample.get('success') or '').strip()
    if success == 'true':
      continue
    elif success == 'false':
      failed[describe(sample)] += 1
    else:
      unreadable += 1

  if not failed and not unreadable:
    print('The warm up walkthrough came back clean, all %d samples succeeded.' % len(samples))
    return

  print('The warm up walkthrough did not come back clean:')
  for sample, count in failed.most_common():
    print('  %dx %s' % (count, sample))
  if unreadable:
    print('  %d row(s) carry neither true nor false in their success column' % unreadable)
  sys.exit('Environment is not healthy, see above. Measuring response times against a broken '
           'application is meaningless. The server side stack trace is in the archived '
           'engine.log, the responses of the failed samples are in %s.' % resultFile)


if __name__ == '__main__':
  main()
