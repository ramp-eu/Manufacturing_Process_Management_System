**Deploy the docker services:**
- Once you have packaged the application (see general README), a **`mpms_7-15-1.0.0-SNAPSHOT.jar`**  file is generated in **`target`** folder.
  So, you basically need the following file structure:

  ![docker package file explorer](https://surfdrive.surf.nl/files/index.php/s/sv1Unera5sZwOtY/download)
- Configure parameters in **`.env`** file
    - e.g., OrionLD CB url:port, DB ports, passwords
- Open a terminal, navigate to the **`docker`** folder and run:
  > `docker-compose up -d --build`
  ![docker-compose up console](https://surfdrive.surf.nl/files/index.php/s/oXUH6icbvH6z56w/download)
- To see the console of the *`shop4cf-mpms`* application, run:
  > `docker logs shop4cf-mpms -f`
  ![camunda-platform logs console](https://surfdrive.surf.nl/files/index.php/s/iE1n0PkpUP4nGHs/download)
    - when you see the following at the end, everything is ready (or search for the red-highlighted logs):
      ![camunda-platform logs console ready](https://surfdrive.surf.nl/files/index.php/s/A7k5683H19F5d81/download)
