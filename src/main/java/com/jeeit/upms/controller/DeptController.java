

package com.jeeit.upms.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jeeit.upms.dto.DeptTree;
import com.jeeit.upms.entity.SysDept;
import com.jeeit.upms.util.R;
import com.jeeit.upms.constant.CommonConstant;
import com.jeeit.upms.service.SysDeptService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author  傅枫
 * @since 2018-01-20
 */
@RestController
@RequestMapping("/dept")
@AllArgsConstructor
public class DeptController {

	private final SysDeptService sysDeptService;

	/**
	 * 通过ID查询
	 *
	 * @param id ID
	 * @return SysDept
	 */
	@GetMapping("/{id}")
	public SysDept get(@PathVariable Integer id) {
		return sysDeptService.selectById(id);
	}


	/**
	 * 返回树形菜单集合
	 *
	 * @return 树形菜单
	 */
	@GetMapping(value = "/tree")
	public List<DeptTree> getTree() {
		SysDept condition = new SysDept();
		condition.setDelFlag(CommonConstant.STATUS_NORMAL);
		return sysDeptService.selectListTree(new EntityWrapper<>(condition));
	}

	/**
	 * 添加
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_add')")
	public R<Boolean> add(@Valid @RequestBody SysDept sysDept) {
		return new R<>(sysDeptService.insertDept(sysDept));
	}

	/**
	 * 删除
	 *
	 * @param id ID
	 * @return success/false
	 */
	@DeleteMapping("/{id}")
	@PreAuthorize("@pms.hasPermission('sys_dept_del')")
	public R<Boolean> delete(@PathVariable Integer id) {
		return new R<>(sysDeptService.deleteDeptById(id));
	}

	/**
	 * 编辑
	 *
	 * @param sysDept 实体
	 * @return success/false
	 */
	@PutMapping
	@PreAuthorize("@pms.hasPermission('sys_dept_edit')")
	public Boolean edit(@Valid @RequestBody SysDept sysDept) {
		sysDept.setUpdateTime(LocalDateTime.now());
		return sysDeptService.updateDeptById(sysDept);
	}
}
