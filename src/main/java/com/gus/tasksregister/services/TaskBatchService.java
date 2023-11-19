package com.gus.tasksregister.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TaskBatchService {

    public void startProcess(MultipartFile file) {
        // TODO Configurar o launcher do job
        // TODO Configurar job e passar arquivo para ele (inicialmente salvando no sistema de arquivos) e utilizando o FlatFileItemReader
        // TODO Passar arquivo para o job diretamente (sem salvar no sistema de arquivos) e utilizando um ItemReader customizado
    }
}
