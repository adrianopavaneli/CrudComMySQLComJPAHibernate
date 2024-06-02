package br.com.pavaneli.application;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.pavaneli.model.dao.JPAUtil;
import br.com.pavaneli.model.entities.Departamento;


public class ProgramDepartamento {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManager();
		
		
		
		System.out.println("\n=== TEST 0: findAll =======");
		String jpql = "select d from Departamento d";
		TypedQuery<Departamento> typedQuery = em.createQuery(jpql, Departamento.class);		
        List<Departamento> list = typedQuery.getResultList();
        for (Departamento d : list) {
            System.out.println(d);
        }
		
      
        
		System.out.println("\n=== TEST 1: insert =======");
        System.out.print("Insira o nome do Departamento: ");
        String nome = sc.nextLine();
        Departamento newDepartamento = new Departamento(null, nome);
        em.getTransaction().begin();
        em.persist(newDepartamento);
        em.getTransaction().commit();
        System.out.println("Inserted!");
        
        System.out.println("\n=== TEST 2: update =======");
        System.out.print("Insira o id do departamento a alterar: ");
        int id1 = sc.nextInt();
        sc.nextLine();
        System.out.print("Insira o novo nome do Departamento: ");
        String nome1 = sc.nextLine();
        Departamento dep3 = new Departamento(id1,nome1);
        em.getTransaction().begin();
        em.merge(dep3);
        em.getTransaction().commit();
        System.out.println("Update completed");
        
        System.out.println("=== TEST 3: findById =======");
        System.out.print("Digite o id do departamento: ");
        int id2 = sc.nextInt();        
        Departamento dep2 = em.find(Departamento.class, id2);
        System.out.println(dep2);


        System.out.println("\n=== TEST 4: delete =======");
        System.out.print("Entre com o id para excluir o departamento: ");
        int id = sc.nextInt();
        Departamento dep1 = em.find(Departamento.class, id);
        em.getTransaction().begin();
        em.remove(dep1);
        em.getTransaction().commit();
        System.out.println("Delete completed");
        
        System.out.println("\n=== TEST 5: findAll com parametro =======");
        System.out.print("Digite o id do departamento: ");
        int idDepartamento = sc.nextInt();
        String jpql1 = "select d from Departamento d where id = :idDepartamento ";
		
		TypedQuery<Departamento> typedQuery1 = em
				.createQuery(jpql1, Departamento.class)
				.setParameter("idDepartamento", idDepartamento);		
        List<Departamento> list1 = typedQuery1.getResultList();
        for (Departamento d : list1) {
            System.out.println(d.getNome());
        }
        
        System.out.println("\n=== TEST 6: Alterar via comando Jpql =======");
        System.out.print("Insira o id do departamento a alterar: ");
        int idDepartamento1 = sc.nextInt();
        sc.nextLine();
        System.out.print("Insira o novo nome do Departamento: ");
        String nomeDepartamento = sc.nextLine();
        em.getTransaction().begin();
        em.createQuery("update Departamento d set d.nome = :nomeDepartamento where d.id = :idDepartamento ")
        .setParameter("idDepartamento", idDepartamento1)
        .setParameter("nomeDepartamento", nomeDepartamento)
        .executeUpdate();
        em.getTransaction().commit();
        
        
		
		
		System.out.println("Pronto");
		sc.close();
		em.close();
		
	}

}
