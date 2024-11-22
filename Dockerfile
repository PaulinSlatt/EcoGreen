FROM ubuntu:latest
LABEL authors="PaulinSlatt"

# Instalação do Nginx como exemplo de servidor
RUN apt-get update && apt-get install -y nginx && apt-get clean

# Exponha a porta 80
EXPOSE 80

# Comando para iniciar o Nginx
CMD ["nginx", "-g", "daemon off;"]
