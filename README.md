# Build
```bash
./gradlew installDist
```
# Run
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

