# Git 명령어

### 가장 자주 쓰는 명령어

`git add .` : 현재 위치한 폴더에서 변경된 작업물을 git(작업대)에 올려주는 역할

`git commit -m "메세지 입력"` :변경된 작업물을 git 상에서 저장해주는 역할

`git push origin master` : 저장된 변경된 작업물을 GitHub master branch에 업로드

`git push origin 브랜치명` : 저장된 변경된 작업물을 GitHub 브랜치명에 업로드

- 상기 4가지 명령어는 GitHub사용에 있어 기본이 되는 명령어
- 보통 master branch에 직접 올리는 경우는 거의 없음
-  개인 브랜치 -> 통합 브랜치 -> 마스터 브랜치 순서로 올라가며 단계별로 담당자의 승인이 필요함

### 프로젝트에서 자주 쓰이는 명령어

`git clone 깃허브 레파지토리 주소` 

- 주소에 해당하는 레파지토리를 내 컴퓨터로 복사해오는 것
- 레파지토리 주소는 반드시 .git으로 끝나야 될것
- 해당 명령어는 마스터 브랜치를 복사해옴

`git clone -b 브랜치명 레파지토리 주소` 

- 주소에 해당하는 브랜치의 레파지토리를 내컴퓨터로 복사해오는 것

`git branch` 

- 새로운 브렌치 생성

`git checkout 브랜치명` 

- 브랜치 이동
- 주의: `git checkout .` 을 사용하면 모든 변경사항이 취소되며 되돌릴 수 없음

`git checkout -b 브랜치명` 

- 새로운 브랜치를 생성하고 이동

`git switch 브랜치명` 

- 브랜치 이동
- checkout의 기능이 너무 많아서 대체로 나온 명령어

`git switch -c 브랜치명` 

- 새로운 브랜치를 생성과 동시에 해당브랜치로 이동

`git pull origin master`

- 마스터 브랜치의 변경사항을 내컴퓨터에 받아오기

`git pull origin 브랜치명`

- 브랜치의 변경사항을 내컴퓨터에 받아오기

`git rm --cached 파일명` 

- 깃허브에서만 파일삭제
- 내컴퓨터에는 파일이 남아 있음

### 자주 쓰지는 않지만 중요한 명령어

`git init` 

- GitHub사용을 위해 최초로 실행하는 명령어
- 최초 1회만 하면 됨, 최상위폴더(~ 또는 root 등)에서 사용금지

`rm -rf .git` , `rm -r .git` 

- 두개 다 git init 취소하는 명령어

`git config --global user.email "본인이메일주소"` 

`git config --global user.name "본인이름(영어)"` 

- GitHub 등록전 최초로 1회 등록해주는 명령어

`git config --global --list` 

- 등록된 config정보 출력

`git touch 파일명.확장자` 

- 파일 생성

`git remote -v` 

- 현재 연결되어있는 git주소 목록보기

`git remote add origin 깃주소` 

- git주소 연결
- .git으로 끝나야됨

`git remote rm origin` 

- 연결된 주소 지우기

