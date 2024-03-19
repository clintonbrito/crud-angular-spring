# CRUD Angular + Spring

This web application is a CRUD developed from [Loiane](https://github.com/loiane) Angular + Spring online course. It allows users to record courses, delete them, update course details, and retrieve a comprehensive list of all courses.

<br>

## 🧪 Technologies

This project was developed using:

  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
  ![Angular](https://img.shields.io/badge/angular-%23DD0031.svg?style=for-the-badge&logo=angular&logoColor=white)
  ![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)
  ![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
  ![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

<br>

## 🚀 Getting started locally

<p style>Clone this repository to your local machine and access the cloned directory:</p>

<pre><code>git clone git@github.com:clintonbrito/crud-angular-spring.git
cd crud-angular-spring</code></pre>

<p>[WORK IN PROGRESS] Run this command to create all the project's Docker containers and install the dependencies:</p>

<pre><code>docker compose up -d</code></pre>

<p>[WORK IN PROGRESS] If you need to stop and remove the project's Docker containers, you can use the following command:</p>

<pre><code>docker compose down</code></pre>

Check the front-end docker logs using the command `docker logs -f app_frontend` which address is running the application and open your browser and access the application through the address below to view the interface locally, for example:

<pre><code>http://localhost:4200/</code></pre>

<br>

## 🎨 Development Patterns

### Commit Patterns

[![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-%23FE5196?logo=conventionalcommits&logoColor=white)](https://conventionalcommits.org)
<a href="https://gitmoji.dev">
  <img
    src="https://img.shields.io/badge/gitmoji-%20😜%20😍-FFDD67.svg?style=flat-square"
    alt="Gitmoji"
  />
</a>

This project adopts [Gitmoji](https://github.com/carloscuesta/gitmoji) and the commit convention known as [Conventional Commits](https://www.conventionalcommits.org/). This means that we follow a standardized format for our commit messages, making it easier to generate changelogs and adopt semantic versioning.

Example commit messages format:

<pre><code>feat: add login functionality
fix: resolve issue with user registration
wip: connecting back-end to front-end</code></pre>

<br>

## 📝 License
This project is licensed under the MIT License. See the <a target="_blank" rel="noopener" href="https://github.com/clintonbrito/crud-angular-spring/blob/main/LICENSE">LICENSE</a> file for details.
