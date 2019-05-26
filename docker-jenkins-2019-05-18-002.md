
https://github.com/jenkinsci/docker/blob/master/README.md


    docker run -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts

    docker run -p 8080:8080 -p 50000:50000 -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts

    docker run -d -v jenkins_home:/var/jenkins_home -p 8080:8080 -p 50000:50000 jenkins/jenkins:lts



https://technologyconversations.com/2017/06/16/automating-jenkins-docker-setup/


--------------------------------------------------------------------------------

https://medium.com/@haikulab/running-jenkins-blueocean-using-docker-755ecc89e6c2


    docker run --name jenkins
    --detach
    --publish 8888:8080
    --volume $HOME/docker/volumes/jenkins:/var/jenkins_home


    docker run --name jenkins -d -p 8888:8080 -v $HOME/docker/volumes/jenkins:/var/jenkins_home jenkinsci/blueocean:latest


--------------------------------------------------------------------------------

https://github.com/jenkinsci/docker/blob/master/README.md
