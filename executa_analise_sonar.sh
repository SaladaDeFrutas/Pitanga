#!/bin/sh

set -euo pipefail

export MAVEN_OPTS="-Xmx1G -Xms128m"
MAVEN_OPTIONS="-B -e -V"

if [ "$TRAVIS_BRANCH" == "master" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ]; then
    echo 'Analyse master branch'

    # Fetch all commit history so that SonarQube has exact blame information
    # for issue auto-assignment
    # This command can fail with "fatal: --unshallow on a complete repository does not make sense"
    # if there are not enough commits in the Git repository (even if Travis executed git clone --depth 50).
    # For this reason errors are ignored with "|| true"
    git fetch --unshallow || true

    mvn org.jacoco:jacoco-maven-plugin:prepare-agent install sonar:sonar \
          $MAVEN_OPTIONS \
          -Dsonar.login=$SONAR_TOKEN

elif [ "$TRAVIS_PULL_REQUEST" != "false" ] && [ -n "${GITHUB_TOKEN:-}" ]; then
    echo 'Internal pull request: trigger QA and analysis'

    mvn org.jacoco:jacoco-maven-plugin:prepare-agent package sonar:sonar \
        $MAVEN_OPTIONS \
        -Dsonar.analysis.mode=preview \
        -Dsonar.branch=$TRAVIS_PULL_REQUEST_BRANCH \
        -Dsonar.github.pullRequest=$TRAVIS_PULL_REQUEST \
        -Dsonar.github.oauth=$SONAR_GITHUB_TOKEN \
        -Dsonar.login=$SONAR_TOKEN
fi

