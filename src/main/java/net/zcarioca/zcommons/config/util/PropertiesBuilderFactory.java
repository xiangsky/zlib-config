/*
 * Project: zlib-config
 * 
 * Copyright (C) 2010 zcarioca.net
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
package net.zcarioca.zcommons.config.util;

/**
 * Factory class for creating instances of {@link PropertiesBuilder}.
 * 
 * @author zcarioca
 */
public final class PropertiesBuilderFactory
{
   private boolean addEnvironmentProperties;
   private boolean addSystemProperties;

   /**
    * Creates a new {@link PropertiesBuilderFactory}.
    * 
    * Sets the <code>addEnvironmentProperties</code> and
    * <code>addSystemProperties</code> to false.
    * 
    * @see PropertiesBuilderFactory#isAddEnvironmentProperties()
    * @see PropertiesBuilderFactory#isAddSystemProperties()
    */
   public PropertiesBuilderFactory()
   {
      this(false, false);
   }

   /**
    * Creates a new {@link PropertiesBuilderFactory}.
    * 
    * @param addEnvironmentProperties The addEnvironmentProperties value.
    * @param addSystemProperties The addSystemProperties value.
    * @see PropertiesBuilderFactory#isAddEnvironmentProperties()
    * @see PropertiesBuilderFactory#isAddSystemProperties()
    */
   public PropertiesBuilderFactory(boolean addEnvironmentProperties, boolean addSystemProperties)
   {
      this.addEnvironmentProperties = addEnvironmentProperties;
      this.addSystemProperties = addSystemProperties;
   }

   /**
    * Determines whether to automatically add all environment properties to
    * {@link PropertiesBuilder} instances generated by this factory.
    * 
    * @param addEnvironmentProperties If <code>true</code>, all environment
    *        properties will be automatically added to generated
    *        PropertiesBuilders, and available for substitution.
    */
   public void setAddEnvironmentProperties(boolean addEnvironmentProperties)
   {
      this.addEnvironmentProperties = addEnvironmentProperties;
   }

   /**
    * Determines whether to automatically add all environment properties to
    * {@link PropertiesBuilder} instances generated by this factory.
    * 
    * @return Returns <code>true</code> if environment properties will be
    *         automatically added to generated PropertiesBuilders;
    *         <code>false</code> otherwise.
    */
   public boolean isAddEnvironmentProperties()
   {
      return this.addEnvironmentProperties;
   }

   /**
    * Determines whether to automatically add all JVM system properties to
    * {@link PropertiesBuilder} instances generated by this factory.
    * 
    * @param addSystemProperties If <code>true</code>, all JVM system properties
    *        will be automatically added to generated PropertiesBuilders, and
    *        available for substitution.
    */
   public void setAddSystemProperties(boolean addSystemProperties)
   {
      this.addSystemProperties = addSystemProperties;
   }

   /**
    * Determines whether to automatically add all JVM system properties to
    * {@link PropertiesBuilder} instances generated by this factory.
    * 
    * @return Returns <code>true</code> if JVM system properties will be
    *         automatically added to generated PropertiesBuilders;
    *         <code>false</code> otherwise.
    */
   public boolean isAddSystemProperties()
   {
      return this.addSystemProperties;
   }

   /**
    * Creates a new instance of {@link PropertiesBuilder}.
    * 
    * @return Returns a new instance of PropertiesBuilder conforming to the
    *         rules of this PropertiesBuilderFactory.
    * @see PropertiesBuilderFactory#isAddEnvironmentProperties()
    * @see PropertiesBuilderFactory#isAddSystemProperties()
    */
   public PropertiesBuilder newPropertiesBuilder()
   {
      PropertiesBuilder builder = new PropertiesBuilder();
      if (isAddEnvironmentProperties())
      {
         builder.addAllEnvironmentProperties();
      }
      if (isAddSystemProperties())
      {
         builder.addAllSystemProperties();
      }
      return builder;
   }
}
