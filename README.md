# KotlinJackson
Check kotlin classes and types with jackson and how it serializes/deserializes them.
## Current data model explanation
```Fake```, ```Check``` classes implements ```IPolymorphic``` interface. 
Such model was selected to demonstrate how complex polymorphic deserialization works. 
Samples of using: ```List<IPolymorphic>```, which in fact can be ```Fake``` or ```Check``` items. 
Simple ```JacksonUnitTest``` verifies correct work of serializing/deserializing.
## Inspired by
1. https://github.com/FasterXML/jackson-module-kotlin/issues/52
2. http://stackoverflow.com/questions/41849708/deserialize-json-with-jackson-with-complex-polymorphic-structure

