package com.projeto.IForum.dto;

import lombok.Data;

@Data
public class RelatoDTO {

    private String tipoRelato; 
    private String categoria; 
    private String assunto;   
    private boolean anonimo;
   
}
