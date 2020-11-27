#! /bin/bash
cd ./api
npm start
osascript -e 'tell application "Terminal" to activate' -e 'tell application "System Events" to tell process "Terminal" to keystroke "t" using command down'
cd ../client
npm start
osascript -e 'tell application "Terminal" to activate' -e 'tell application "System Events" to tell process "Terminal" to keystroke "t" using command down'
cd ../rtmpserver
npm start
