### `gitinfo.sh`

Script for gitinfo (gitinfo.sh):

* https://gist.github.com/thomasychen/963891

### `git_set_global_user_profile.sh`

Use the following to set `user.name`/`user.email` for projects/profiles:

    git config --global user.name "Rob Powell"
    git config --global user.email "rppowellemail@gmail.com"

### `git_set_local_user_profile.sh`

Use the following to set `user.name`/`user.email` for projects/profiles:

    git config --local user.name "Rob Powell"
    git config --local user.email "rppowellemail@gmail.com"
    git config --local credential.helper ""

### `git_pull_remotes.sh`

Use the following to pull all remote branches:

    for remote in `git branch -r | grep -v "\->"`; do git branch --track ${remote#origin/} $remote; done
