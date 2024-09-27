package com.fadev.antiantiApe.service.category;

import com.fadev.antiantiApe.exception.AlreadyExistsException;
import com.fadev.antiantiApe.exception.ResourceNotFoundException;
import com.fadev.antiantiApe.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.fadev.antiantiApe.repository.categoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements iCategoryService{
    private final categoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository::save)
                .orElseThrow(() -> new AlreadyExistsException(category.getName() + "already exist"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id))
                .map(oldCategory -> {
                    oldCategory.setName(category.getName());
                    return categoryRepository.save(oldCategory);
                }).orElseThrow(() -> new ResourceNotFoundException("Category Not Found!"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository
                .findById(id)
                .ifPresentOrElse(
                        categoryRepository::delete,
                        () -> {
                                throw new ResourceNotFoundException("Category Not Found!");
                        });
    }
}
