package com.tsyapa.data.entity.mapper.base;

public abstract class BaseMapper<Entity, Model> {

    public abstract Model transform(Entity entity);

    public abstract Entity retransform(Model model);
}