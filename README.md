# Overview
This repository demonstrates how to set up a Jenkins-based CI/CD flow where Docker is used to provide consistent build/test environments.
This project is ideal for practising pipelines, containerised automation and DevOps workflows.

# Project Structure
- pipelines/ --> It contains Jenkins pipeline scripts (Groovy) defining stages: checkout, build, test, publish

# Configuration
- Clone the repo
  ```bash
  git clone https://github.com/Ashik224/jenkins-setup-for-CI-CD.git
  cd jenkins-setup-for-CI-CD
  ```
- Launch Jenkins (locally or in Docker) and create a new pipeline job pointing to this repo
- If jenkins is run locally, ensure the docker engine is running
- Run the pipeline
- View build history, logs and results in Jenkins UI
