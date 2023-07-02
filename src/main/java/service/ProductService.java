package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dto.ProductDTO;
import entity.ProductyEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repository.ProductRepository;

@ApplicationScoped
public class ProductService {

	@Inject
	ProductRepository productRepository;
	
	public List<ProductDTO> getAllProducts(){
		List<ProductDTO> products = new ArrayList<ProductDTO>();
		productRepository.findAll().stream().forEach(item -> {
			products.add(mapProductEntityToDTO(item));
		});
		
		return products;
	}
	
	public ProductDTO findProductById(Long id) {
		return mapProductEntityToDTO(productRepository.findById(id));
	}
	
	
	public void createProduct(ProductDTO dto) {
		productRepository.persist(dtoToEntity(dto));
	}
	
	public void changeProduct(Long id, ProductDTO dto) {
		ProductyEntity entity = productRepository.findById(id);
		
		if(Objects.nonNull(entity)) {
			entity.setCategory(dto.getCategory());
			entity.setDescription(dto.getDescription());
			entity.setModel(dto.getModel());
			entity.setName(dto.getName());
			entity.setPrice(dto.getPrice());
			
			productRepository.persist(entity);
		}
		
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	private ProductDTO mapProductEntityToDTO(ProductyEntity entity){
		ProductDTO dto = new ProductDTO();
		dto.setCategory(entity.getCategory());
		dto.setDescription(entity.getDescription());
		dto.setModel(entity.getModel());
		dto.setName(entity.getName());
		dto.setPrice(entity.getPrice());
		return dto;
	}
	
	private ProductyEntity dtoToEntity(ProductDTO dto) {
		ProductyEntity entity = new ProductyEntity();
		entity.setCategory(dto.getCategory());
		entity.setDescription(dto.getDescription());
		entity.setModel(dto.getModel());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		return entity;
	}
}
