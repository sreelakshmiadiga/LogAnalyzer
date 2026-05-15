*Ollama set up*

a] *On local machine*

Download: Go to the below link
ollama.com/download/mac.

Install: Unzip Ollama-darwin.zip and move the Ollama app to your Applications folder.

Launch: Double-click the app. You'll see a 🦙 icon in your top Menu Bar.

Pull: Now you can run below command on your terminal 
ollama pull llama3.2 

b] *Alternative : Pulling Ollama docker image via colima*

1. brew install colima docker

2. docker run -d \
  -v ollama_data:/root/.ollama \
  -p 11434:11434 \
  --name ollama \
  ollama/ollama

3. docker exec -it ollama ollama pull llama3.2
