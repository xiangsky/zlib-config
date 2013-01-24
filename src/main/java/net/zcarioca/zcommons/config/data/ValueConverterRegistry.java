/*
 * Project: zlib-config
 * 
 * Copyright (C) 2013 zcarioca.net
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.zcarioca.zcommons.config.data;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.zcarioca.zcommons.config.exceptions.ConfigurationException;

/**
 * A registry of value converters. Custom converters should be supplied to this registry. 
 * and 
 * 
 * @author zcarioca
 */
public class ValueConverterRegistry
{
   private static ValueConverterRegistry valueConverterRegistry;
   
   private Map<Class<?>, PropertyConverter<?>> registry;
   
   /**
    * Gets access to the {@link ValueConverterRegistry}.
    * 
    * @return Returns access to the {@link ValueConverterRegistry}.
    */
   public static ValueConverterRegistry getRegistry()
   {
      if (valueConverterRegistry == null)
      {
         valueConverterRegistry = new ValueConverterRegistry();
      }
      return valueConverterRegistry;
   }
   
   /**
    * Registers a new {@link PropertyConverter}.
    * 
    * @param converter The {@link PropertyConverter} to register.
    * 
    * @throws IllegalArgumentException if the converter is null.
    * @throws ConfigurationException if the converter does not return a supported class.
    */
   public void register(PropertyConverter<?> converter) throws ConfigurationException
   {
      if (converter == null)
      {
         throw new IllegalArgumentException("The property converter cannot be null");
      }
      
      Class<?> supportedClass = converter.getSupportedClass();
      if (supportedClass != null)
      {
         this.registry.put(supportedClass, converter);
      }
      else
      {
         throw new ConfigurationException("Cannot use a property converter that does not return a supported class: " + converter.getClass());
      }
   }
   
   /**
    * Gets a {@link PropertyConverter} for the supplied type.
    * 
    * @param type The type to convert.
    * @return Returns a {@link PropertyConverter} for the supplied type.
    * 
    * @throws IllegalArgumentException if the type is null.
    */
   public PropertyConverter<?> getPropertyConverter(Class<?> type) 
   {
      if (type == null)
      {
         throw new IllegalArgumentException("The supplied type cannot be null");
      }
      
      if (this.registry.containsKey(normalizedType(type)))
      {
         return this.registry.get(normalizedType(type));
      }
      
      return new GenericPropertyConverter();
   }
   
   private Class<?> normalizedType(Class<?> type)
   {
      if (type.isArray())
      {
         return normalizedType(type.getComponentType());
      }
      if (type.isPrimitive())
      {
         return convertFromPrimitiveType(type);
      }
      return type;
   }
   
   private Class<?> convertFromPrimitiveType(Class<?> type)
   {
      if (boolean.class == type)
         return Boolean.class;
      if (char.class == type)
         return Character.class;
      if (byte.class == type)
         return Byte.class;
      if (short.class == type)
         return Short.class;
      if (int.class == type)
         return Integer.class;
      if (long.class == type)
         return Long.class;
      if (float.class == type)
         return Float.class;
      if (double.class == type)
         return Double.class;
      
      return null;
   }
   
   ValueConverterRegistry()
   {
      registry = new HashMap<Class<?>, PropertyConverter<?>>();
      registry.put(String.class, new StringPropertyConverter());
      registry.put(Boolean.class, new BooleanPropertyConverter());
      registry.put(Character.class, new CharacterPropertyConverter());
      registry.put(Byte.class, new NumberPropertyConverter<Byte>(Byte.class));
      registry.put(Short.class, new NumberPropertyConverter<Short>(Short.class));
      registry.put(Integer.class, new NumberPropertyConverter<Integer>(Integer.class));
      registry.put(Long.class, new NumberPropertyConverter<Long>(Long.class));
      registry.put(Float.class, new NumberPropertyConverter<Float>(Float.class));
      registry.put(Double.class, new NumberPropertyConverter<Double>(Double.class));
      registry.put(Date.class, new DatePropertyConverter());
      registry.put(Calendar.class, new CalendarPropertyConverter());
   }
}
