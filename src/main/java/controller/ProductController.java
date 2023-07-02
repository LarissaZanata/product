package controller;

import java.util.List;


import dto.ProductDTO;
import jakarta.ws.rs.Produces;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ProductService;

@Path("/api/products")
public class ProductController {
	
	@Inject
	ProductService productService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductDTO> findAllProducts(){
		return productService.getAllProducts();
	}
	
	@GET
	@Path("/{id}")
	public ProductDTO findCustomerById(@PathParam("id") Long id) {
		return productService.findProductById(id);
	}
	
	@POST
	@Transactional
	public Response saveProduct(ProductDTO dto) {
		try {
			productService.createProduct(dto);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@PUT
	@Path("/{id}")
	@Transactional
	public Response changeProduct(@PathParam("id") Long id, ProductDTO dto) {
		try {
			productService.changeProduct(id, dto);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Transactional
	public Response deleteProduct(@PathParam("id") Long id) {
		try {
			productService.deleteProduct(id);
			return Response.ok().build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.serverError().build();
		}
	}

}
