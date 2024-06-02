package br.com.pavaneli.application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.pavaneli.model.dao.JPAUtil;
import br.com.pavaneli.model.entities.Departamento;
import br.com.pavaneli.model.entities.Vendedor;

public class ProgramVendedor {

	public static void main(String[] args) {
		DateTimeFormatter formatter= DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Scanner sc = new Scanner(System.in);
		EntityManager em = new JPAUtil().getEntityManager();
		
		
		
		System.out.println("\n=== TEST 0: findAll =======");
		String jpql = "select v from Vendedor v";
		TypedQuery<Vendedor> typedQuery = em.createQuery(jpql, Vendedor.class);		
        List<Vendedor> list = typedQuery.getResultList();
        for (Vendedor d : list) {
            System.out.println(d);
        }
		
      
        
		System.out.println("\n=== TEST 1: insert =======");
		System.out.print("Digite o nome do vendedor: ");
        String nome = sc.nextLine();
        System.out.print("Digite o email do vendedor: ");
        String email = sc.nextLine();

        System.out.print("Digite a data de aniversário do vendedor(aaaa-MM-dd): ");
        String dataString = sc.nextLine();
        LocalDate data = LocalDate.parse(dataString, formatter);

        System.out.print("Digite o salario base do vendedor: ");
        double salario = sc.nextDouble();
        System.out.print("Digite o id do departamento do vendedor: ");
        int departamento = sc.nextInt();

        Vendedor newVendedor = new Vendedor(null, nome,email,data,salario,new Departamento(departamento,null));
        em.getTransaction().begin();
        em.persist(newVendedor);
        em.getTransaction().commit();
        System.out.println("Inserted!");
        
        System.out.println("\n=== TEST 2: update =======");
        System.out.print("Insira o id do vendedor a alterar: ");
        int id1 = sc.nextInt();
        sc.nextLine();
		System.out.print("Digite o nome do vendedor: ");
        String nome1 = sc.nextLine();
        System.out.print("Digite o email do vendedor: ");
        String email1 = sc.nextLine();

        System.out.print("Digite a data de aniversário do vendedor(aaaa-MM-dd): ");
        String dataString1 = sc.nextLine();
        LocalDate data1 = LocalDate.parse(dataString1, formatter);

        System.out.print("Digite o salario base do vendedor: ");
        double salario1 = sc.nextDouble();
        System.out.print("Digite o id do departamento do vendedor: ");
        int departamento1 = sc.nextInt();

        Vendedor updateVendedor = new Vendedor(id1, nome1,email1,data1,salario1,new Departamento(departamento1,null));
        em.getTransaction().begin();
        em.merge(updateVendedor);
        em.getTransaction().commit();
        System.out.println("Update Completed!");
        
        
        System.out.println("=== TEST 3: findById =======");
        System.out.print("Digite o id do vendedor: ");
        int id2 = sc.nextInt();        
        Vendedor dep2 = em.find(Vendedor.class, id2);
        System.out.println(dep2);


        System.out.println("\n=== TEST 4: delete =======");
        System.out.print("Entre com o id para excluir o vendedor: ");
        int id = sc.nextInt();
        Vendedor dep1 = em.find(Vendedor.class, id);
        em.getTransaction().begin();
        em.remove(dep1);
        em.getTransaction().commit();
        System.out.println("Delete completed");
        
        System.out.println("\n=== TEST 5: findAll com parametro =======");
        System.out.print("Digite o id do vendedor: ");
        int idVendedor = sc.nextInt();
        String jpql1 = "select d from Vendedor d where id = :idVendedor ";
		
		TypedQuery<Vendedor> typedQuery1 = em
				.createQuery(jpql1, Vendedor.class)
				.setParameter("idVendedor", idVendedor);		
        List<Vendedor> list1 = typedQuery1.getResultList();
        for (Vendedor d : list1) {
            System.out.println(d);
        }
        
        System.out.println("\n=== TEST 6: Alterar via comando Jpql =======");
        System.out.print("Insira o id do vendedor a alterar: ");
        int idVendedor1 = sc.nextInt();
        sc.nextLine();
        System.out.print("Insira o novo nome do Vendedor: ");
        String nomeVendedor = sc.nextLine();
        em.getTransaction().begin();
        em.createQuery("update Vendedor d set d.nome = :nomeVendedor where d.id = :idVendedor ")
        .setParameter("idVendedor", idVendedor1)
        .setParameter("nomeVendedor", nomeVendedor)
        .executeUpdate();
        em.getTransaction().commit();
        
        
		
		
		System.out.println("Pronto");
		sc.close();
		em.close();
	}

}
