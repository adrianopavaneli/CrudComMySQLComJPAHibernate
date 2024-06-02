package br.com.pavaneli.model.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Vendedor implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
    private String nome;
    private String email;
    private LocalDate dataAniversario;
    private Double salarioBase;
    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;
    
    public Vendedor() {
    }

	public Vendedor(Integer id, String nome, String email, LocalDate dataAniversario, Double salarioBase,
			Departamento departamento) {
		
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.dataAniversario = dataAniversario;
		this.salarioBase = salarioBase;
		this.departamento = departamento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataAniversario() {
		return dataAniversario;
	}

	public void setDataAniversario(LocalDate dataAniversario) {
		this.dataAniversario = dataAniversario;
	}

	public Double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(Double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vendedor other = (Vendedor) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Vendedor [id=" + id + ", nome=" + nome + ", email=" + email + ", dataAniversario=" + dataAniversario
				+ ", salarioBase=" + salarioBase + ", departamento=" + departamento + "]";
	}
	
    

}
