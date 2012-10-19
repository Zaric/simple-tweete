#!/bin/bash
echo Running application...
${JBOSS_HOME:?"Need to set JBOSS_HOME"}
echo JBoss found at $JBOSS_HOME
echo Coping war to JBoss default instance
cp -fr target/sm-tweete.war $JBOSS_HOME/server/default/deploy
echo Starting JBoss
echo Open this link in your browser http://localhost:8080/sm-tweete/
$JBOSS_HOME/bin/run.sh -c default