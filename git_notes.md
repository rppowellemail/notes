Script for gitinfo (gitinfo.sh):

* https://gist.github.com/thomasychen/963891

.h2 `git_set_profile.sh`

Use the following to set `user.name`/`user.email` for projects/profiles:

    git config --global user.name "Rob Powell"
    git config --global user.email "rppowellemail@gmail.com"

.h2 `git_pull_remotes.sh`

Use the following to pull all remote branches:

    for remote in `git branch -r | grep -v "\->"`; do git branch --track ${remote#origin/} $remote; done
