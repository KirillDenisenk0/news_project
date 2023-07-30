package myProject.mapper;

public interface Mapper<D,E> {
    E mapToEntity(D d);
}