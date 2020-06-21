`git_pull_remotes.sh`

Use the following to pull all remote branches:

    for remote in `git branch -r | grep -v "\->"`; do git branch --track ${remote#origin/} $remote; done
