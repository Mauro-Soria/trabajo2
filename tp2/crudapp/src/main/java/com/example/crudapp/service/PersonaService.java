package com.example.crudapp.service;

import com.example.crudapp.model.Persona;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {
    private List<Persona> personas = new ArrayList<>();
    private Long nextId = 1L;

    public PersonaService() {
        personas.add(new Persona(nextId++, "Nicolas", "Vassallo", 39));
        personas.add(new Persona(nextId++, "Enzo", "Lanati", 20));
    }

    public List<Persona> getAllPersonas() { return personas; }

    public Optional<Persona> getPersonaById(Long id) {
        return personas.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Persona addPersona(Persona persona) {
        persona.setId(nextId++);
        personas.add(persona);
        return persona;
    }

    public Persona updatePersona(Long id, Persona updatedPersona) {
        Optional<Persona> optionalPersona = getPersonaById(id);
        if (optionalPersona.isPresent()) {
            Persona persona = optionalPersona.get();
            persona.setNombre(updatedPersona.getNombre());
            persona.setApellido(updatedPersona.getApellido());
            persona.setEdad(updatedPersona.getEdad());
            return persona;
        }
        return null;
    }

    public boolean deletePersona(Long id) {
        return personas.removeIf(p -> p.getId().equals(id));
    }
}
