package webservices;

import entities.Module;
import entities.UniteEnseignement;
import metiers.ModuleBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/module")
public class ModuleRestAPI {

    static ModuleBusiness helper = new ModuleBusiness();


    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListModules() {
        return Response.status(200)
                .entity(helper.getAllModules())
                .build();
    }


    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response addModule(Module module) {

        if (helper.addModule(module)) {
            return Response.status(201)
                    .entity("Succes")
                    .build();
        } else {
            return Response.status(400)
                    .entity("Erreur")
                    .build();
        }
    }


    @Path("/{matricule}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModuleByMatricule(
            @PathParam("matricule") String matricule) {

        Module module = helper.getModuleByMatricule(matricule);

        if (module != null) {
            return Response.status(200)
                    .entity(module)
                    .build();
        } else {
            return Response.status(404)
                    .entity("Module not found")
                    .build();
        }
    }


    @Path("/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByType(
            @QueryParam("type") Module.TypeModule type) {

        return Response.status(200)
                .entity(helper.getModulesByType(type))
                .build();
    }


    @Path("/ue/{code}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getModulesByUE(
            @PathParam("code") int code) {

        UniteEnseignement ue = new UniteEnseignement();
        ue.setCode(code);

        return Response.status(200)
                .entity(helper.getModulesByUE(ue))
                .build();
    }


    @Path("/{matricule}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response updateModule(
            @PathParam("matricule") String matricule,
            Module module) {

        if (helper.updateModule(matricule, module)) {
            return Response.status(200)
                    .entity("updated successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("failed update")
                    .build();
        }
    }


    @Path("/{matricule}")
    @DELETE
    public Response deleteModule(
            @PathParam("matricule") String matricule) {

        if (helper.deleteModule(matricule)) {
            return Response.status(200)
                    .entity("deleted successfully")
                    .build();
        } else {
            return Response.status(404)
                    .entity("delete failed")
                    .build();
        }
    }
}