package br.com.primeira.avaliacao.management;

import java.util.Collection;
import java.util.Locale;
import java.util.Scanner;

import br.com.primeira.avaliacao.model.Client;
import br.com.primeira.avaliacao.model.Product;
import br.com.primeira.avaliacao.service.ClientService;
import br.com.primeira.avaliacao.service.ProductService;


public class ManagementSystem {
	
	private Scanner sc;
	private StringBuilder sb;
	private String option;
	private ClientService clientService;
	private ProductService productService;
	
	public ManagementSystem() {
		clientService = new ClientService();
		productService = new ProductService();
		sc = new Scanner(System.in);
		Locale.setDefault(Locale.US); // Utilizar "." para digitar números decimais.          
	}
	
	public void menuMain() {
		this.option = "";
		
		while(this.option.isEmpty() || this.option.isBlank()) {
			System.out.println("\n-------------------------------\n");
			System.out.println("Options:");
			System.out.println("1) Client");
			System.out.println("2) Product");
			System.out.println("0) Close System");
			System.out.println("\n-------------------------------\n");
			
			System.out.print("Choice: ");
			this.option = sc.next();
		}
		optionsMenuMain(this.option);
	}

	private void optionsMenuMain(String opt) {
		Integer op = Integer.parseInt(opt);
		if(op == 1) {
			menuOptionClient();
		}
		else if(op == 2) {
			menuOptionProduct();
		}
		else if(op == 0) {
			closeSystem();
		}
		else {
			System.err.println("Sorry, Invalid Option");
			menuMain();
		}
		
	}

	private void menuOptionClient() {
		this.option = "";
		while(this.option.isEmpty() || this.option.isBlank()) {
			System.out.println("\n-------------------------------\n");
			System.out.println("Options Client:");
			System.out.println("1) List Client");
			System.out.println("2) Find Client");
			System.out.println("3) Save Client");
			System.out.println("4) Update Client");
			System.out.println("5) Delete Client");
			System.out.println("#) Back Menu");
			System.out.println("0) Close System");
			System.out.println("\n-------------------------------\n");
			
			System.out.print("Choice: ");
			this.option = sc.next();
		}
		optionsMenuClient(option);
	}

	private void optionsMenuClient(String opt) {
		Integer op = opt.equals("#") ? null : Integer.parseInt(opt);
		Client client = null;
		if(op == null) {
			menuMain();
		}
		else if(op == 0) {
			closeSystem();
		}
		else if(op == 1) {
			
			System.out.println(mountList(clientService.findAll(), "Clients"));
			menuOptionClient();
		}
		else if(op == 2) {
			Long id = null;
			String writer = null; 
			
			System.out.println("\n-----------Find Client-----------\n");
			
			while(id == null) {
				System.out.println("Enter with code Client: ");
				writer = sc.next();
				id = writer.isBlank() ? null : Long.parseLong(writer);
			}
			
			System.out.println(clientService.findById(id).get());
			menuOptionClient();
		}
		else if(op == 3) {
			client = new Client();
			
			System.out.println("\n----------Sign up Form----------");
			System.out.println("---Enter 0 to stop the process---\n");
			
			while(client.getNome() == null) {
				System.out.println("Enter with Name: ");
				client.setNome(sc.next());
			}
		
			if(client.getNome().equals("0")) {
				optionsMenuMain("1");
			}
			else {
				while(client.getCpf() == null) {
					System.out.println("Enter with the CPF: ");
					client.setCpf(sc.next());
				}
			}
			
			if(client.getCpf().equals("0")) {
				menuOptionClient();
			}
			else {
				clientService.save(client);
				
				System.out.println("Client Saving");
				menuOptionClient();
			}
		}
		else if(op == 4) {
			client = new Client();
			Long id = null;
			String writer = "";
			
			System.out.println("\n-------Update Client  Form------");
			System.out.println("---Enter 0 to stop the process---\n");
			
			while(id == null) {
				System.out.println("Enter with code the Client: ");
				writer = sc.next();
				id = writer.isBlank() ? null :  Long.parseLong(writer);
			}
			
			if(id == 0) {
				menuOptionClient();
			}
			else {
				while(client.getNome() == null || client.getNome().isBlank()) {
					System.out.println("Enter with Name: ");
					sc.nextLine();
					client.setNome(sc.nextLine());
				}
			}
			
			if(client.getNome().equals("0")) {
				menuOptionClient();
			}
			else {
				while(client.getCpf() == null || client.getNome().isBlank()) {
					System.out.println("Enter with the CPF: ");
					sc.nextLine();
					client.setCpf(sc.nextLine());
				}
			}
			
			if(client.getCpf().equals("0")) {
				menuOptionClient();
			}
			else {
				clientService.update(id, client);
				System.out.println("Update Client with Success");
				menuOptionClient();
			}
			
		}
		else if(op == 5) {
			Long id = null;
			String writer= "";
			
			System.out.println("\n-----------Delete Client-----------\n");
			
			while (id == null) {
				System.out.println("Enter with code Client for delete: ");
				writer = sc.next();
				id = writer.isBlank() ? null : Long.parseLong(writer);
			}
			clientService.delete(id);
			System.out.println("Client Deleted with Success");
			menuOptionClient();
		}
		else {
			System.err.println("Sorry, Invalid Option");
			menuOptionClient();
		}
		
	}

	private void menuOptionProduct() {
		this.option = "";
		while(this.option.isEmpty() || this.option.isBlank()) {
			System.out.println("\n-------------------------------\n");
			System.out.println("Options Product:");
			System.out.println("1) List Product");
			System.out.println("2) Find Product");
			System.out.println("3) Save Product");
			System.out.println("4) Update Product");
			System.out.println("5) Delete Product");
			System.out.println("#) Back Menu");
			System.out.println("0) Close System");
			System.out.println("\n-------------------------------\n");
			
			System.out.print("Choice: ");
			this.option = sc.next();
		}
		optionsMenuProduct(option);
	}
	
	private void optionsMenuProduct(String opt) {
		Integer op = opt.equals("#") ? null : Integer.parseInt(opt);
		Product product;
		if(op == null) {
			menuMain();
		}
		else if(op == 0) {
			closeSystem();
		}
		else if(op == 1) {
			System.out.println(mountList(productService.findAll(), "Product"));
			menuOptionProduct();
		}
		else if(op == 2) {
			Long id = null;
			String writer = "";
			
			System.out.println("\n-----------Find Product----------\n");
			
			while(id == null) {
				System.out.println("Enter with code Product: ");
				writer = sc.next();
				id = writer.isBlank() ? null : Long.parseLong(writer);
			}
			
			System.out.println(productService.findById(id).get());
			menuOptionProduct();
		}
		else if(op == 3) {
			product = new Product();
			String write = "";
			System.out.println("\n------Sign up Product Form-------");
			System.out.println("---Enter 0 to stop the process---\n");
			
			while(product.getDescription() == null) {
				System.out.println("Enter with Description Product: ");
				product.setDescription(sc.next());
			}
		
			if(product.getDescription().equals("0")) {
				menuOptionProduct();
			}
			else {
				while(product.getPrice() == null) {
					System.out.println("Enter with the Price Product: ");
					write = sc.next();
					product.setPrice(write.isBlank() ? null : Double.parseDouble(write));
				}
			}
			
			if(product.getPrice() == 0){
				menuOptionProduct();
			}
			else {
				while(product.getBarCode() == null) {
					System.out.println("Enter with the Bar Code of the Product: ");
					sc.nextLine();
					product.setBarCode(sc.nextLine());
				}
			}
			
			if(product.getBarCode().equals("0")) {
				menuOptionProduct();
			}
			else {
				productService.save(product);
				
				System.out.println("Product Saving");
				menuOptionProduct();
			}
		}
		else if(op == 4) {
			product = new Product();
			Long id = null;
			String write = "";
			System.out.println("\n-------Update Client  Form-------");
			System.out.println("---Enter 0 to stop the process---\n");
			
			while(id == null) {
				System.out.println("Enter with code the Product: ");
				write = sc.next();
				id = write.isBlank() ? null : Long.parseLong(write);
			}
			
			if(id == 0) {
				menuOptionProduct();
			}
			else {
				while(product.getDescription() == null) {
					System.out.println("Enter with Description Product: ");
					sc.nextLine();
					product.setDescription(sc.nextLine());
				}
			}
			
			if(product.getDescription().equals("0")) {
				menuOptionProduct();
			}
			else {
				write = "";
				while(product.getPrice() == null) {
					System.out.println("Enter with the Price Product: ");
					write = sc.next();
					product.setPrice(write.isBlank() ? null :  Double.parseDouble(write));
				}
			}
			
			if(product.getPrice() == 0){
				menuOptionProduct();
			}
			else {
				while(product.getBarCode() == null) {
					System.out.println("Enter with the Bar Code of the Product: ");
					sc.nextLine();
					product.setBarCode(sc.nextLine());
				}
			}
			
			if(product.getBarCode().equals("0")) {
				menuOptionProduct();
			}
			else {
				productService.update(id, product);
				System.out.println("Update Product with Success");
			}
			
		}
		else if(op == 5) {
			Long id = null;
			String writer= "";
			
			System.out.println("\n-----------Delete Product-----------\n");
			
			while (id == null) {
				System.out.println("Enter with code the Product for delete: ");
				writer = sc.next();
				id = writer.isBlank() ? null : Long.parseLong(writer);
			}
			productService.delete(id);
			System.out.println("Product Deleted with Success");
			menuOptionProduct();
		}
		else {
			System.err.println("Sorry, Invalid Option");
			menuOptionProduct();
		}
		
	}

	private void closeSystem() {
		sc.close();
		System.exit(0);
	}
	
	private String mountList(Collection<?> list, String title) {
		sb = new StringBuilder();
		sb.append("\n-----------List "+ title +"----------\n");
		list.forEach(i -> sb.append(i + "\n"));
		return sb.toString();
		
	}
}
