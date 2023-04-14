# Projeto DTI

We are using version 16 of jdk as recommended in the TP class.
Moreover the commands we are using are for unix.

# Build

In the root of the project run:

```bash
./gradlew installDist
```
# Run

In the root/build/install/Projeto2 run:

Run at least 4 replicas:
```bash
./smartrun.sh intol.DTI.DTIServer 0
```
```bash
./smartrun.sh intol.DTI.DTIServer 1
```
```bash
./smartrun.sh intol.DTI.DTIServer 2
```
```bash
./smartrun.sh intol.DTI.DTIServer 3
```
After these 4 (or more) replicas, run some clients, for example:
```bash
./smartrun.sh intol.DTI.DTIInterface 4
```
```bash
./smartrun.sh intol.DTI.DTIInterface 5
```

