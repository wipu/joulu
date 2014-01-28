#!/bin/bash

set -eu

[ $# == 1 ] || {
  echo "Usage: $0 REV" >&2
  echo "e.g.   $0 603" >&2
  exit 1
}

HERE=$(dirname "$0")
cd "$HERE"

REV=$1
URL=https://svn.code.sf.net/p/iwant/code/trunk

svn export --force -r "$REV" "$URL/iwant-distillery/as-some-developer/with"

CONF=i-have/conf
mkdir -p "$CONF"
echo "iwant-from=$URL@$REV" > "$CONF/iwant-from"
