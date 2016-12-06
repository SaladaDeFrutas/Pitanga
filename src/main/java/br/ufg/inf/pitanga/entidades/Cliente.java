package br.ufg.inf.pitanga.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente extends Usuario {
}
