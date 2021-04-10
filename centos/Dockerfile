FROM centos:latest
#run open ssh server
RUN  yum -y install openssh-server
#create a user and add password 
RUN useradd remote_user && \
	echo "remote_user:1234" | chpasswd && \
	mkdir /home/remote_user/.ssh && \
	chmod 700 /home/remote_user/.ssh
#copy ssh key to path
COPY remote-key.pub /home/remote_user/.ssh/authorized_keys
#make the user created the owner of the .ssh folder. Give Read/Write access to authorized keys folder.
RUN chown remote_user:remote_user -R /home/remote_user && \
	chmod 400 /home/remote_user/.ssh/authorized_keys
#allow ssh to create global keys
RUN ssh-keygen -A
#instruct docker how service should be started
CMD /usr/sbin/sshd -D

