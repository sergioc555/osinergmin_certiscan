package com.certicom.certiscan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.certicom.certiscan.domain.CheckListImagen;




public interface  CheckListImagenMapper {

	@Select("select * from t_checklist_imagen e where e.id_chk_imagen = #{p_id_chk_imagen}")
	public CheckListImagen findById(@Param("p_id_chk_imagen") Integer codigoCheckListImagen) throws Exception;
	
	@Select("select * from t_checklist_imagen")
	public List<CheckListImagen> findAll() throws Exception;
	
	@Select("select * from t_checklist_imagen e where e.id_checklist = #{p_id_checklist}")
	public List<CheckListImagen> findByIdCheckList(@Param("p_id_checklist") Integer codigoCheckList) throws Exception;
	
	@Insert("insert into t_checklist_imagen (expediente_id, ruta,  id_checklist, idusuario) values (#{expediente_id},#{ruta},#{id_checklist},#{idusuario})")
	public void crearCheckListImagen(CheckListImagen checkListImagen) throws Exception;
	
	@Update("update t_checklist_imagen set expediente_id = #{expediente_id}, ruta = #{ruta},  id_checklist = #{id_checklist},  idusuario = #{idusuario} where id_chk_imagen= #{id_chk_imagen}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCheckListImagen(CheckListImagen checkListImagen) throws Exception;
	
	@Update("update t_checklist_imagen set observacion = #{observacion} where id_chk_imagen= #{id_chk_imagen}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCheckListImagenObs(CheckListImagen checkListImagen) throws Exception;
	
	@Update("update t_checklist_imagen set ruta = #{ruta} where id_chk_imagen= #{id_chk_imagen}")
	@Options(flushCache=true,useCache=true)
    public void actualizarCheckListImagenArchivo(CheckListImagen checkListImagen) throws Exception;
	
	@Delete("delete from t_checklist_imagen  where id_chk_imagen = #{id_chk_imagen}")
	@Options(flushCache=true)
	public void eliminarCheckListImagen(@Param("id_chk_imagen") Integer id_checklist_imagen) throws Exception;
	
	
	@Select("select * from t_checklist_imagen e where e.id_checklist = #{p_id_checklist} and e.expediente_id = #{expediente_id}")
	public List<CheckListImagen> findByIdCheckListExpediente_id(@Param("p_id_checklist") Integer codigoCheckList,@Param("expediente_id") Integer expediente_id) throws Exception;
	
	/*@Select("select * from t_empresa")
	public List<CheckList> listaEmpresasActivas()throws Exception;*/
		
	//No permite eliminar un Empresa Asociado a un Proyecto devuelve la cantidad de Empresa asociado
	/*@Select("select count(*) from t_centros_atencion where id_empresa =  #{id_empresa} ")	
	public Integer listProyectoxEmpresa(@Param("id_empresa") Integer id_empresa) throws Exception;	 */
	
}
