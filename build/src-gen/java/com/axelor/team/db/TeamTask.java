package com.axelor.team.db;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;

import com.axelor.apps.account.db.InvoiceLine;
import com.axelor.apps.base.db.Currency;
import com.axelor.apps.base.db.Frequency;
import com.axelor.apps.base.db.Product;
import com.axelor.apps.base.db.Timer;
import com.axelor.apps.base.db.Unit;
import com.axelor.apps.project.db.Project;
import com.axelor.apps.project.db.ProjectPlanningTime;
import com.axelor.apps.project.db.TeamTaskCategory;
import com.axelor.apps.project.db.TeamTaskTag;
import com.axelor.apps.sale.db.SaleOrderLine;
import com.axelor.auth.db.AuditableModel;
import com.axelor.auth.db.User;
import com.axelor.db.annotations.NameColumn;
import com.axelor.db.annotations.Track;
import com.axelor.db.annotations.TrackEvent;
import com.axelor.db.annotations.TrackField;
import com.axelor.db.annotations.TrackMessage;
import com.axelor.db.annotations.Widget;
import com.axelor.meta.db.MetaFile;
import com.google.common.base.MoreObjects;

@Entity
@Table(name = "TEAM_TASK", indexes = { @Index(columnList = "team"), @Index(columnList = "name"), @Index(columnList = "assigned_to"), @Index(columnList = "frequency"), @Index(columnList = "next_team_task"), @Index(columnList = "project"), @Index(columnList = "fullName"), @Index(columnList = "parent_task"), @Index(columnList = "team_task_category"), @Index(columnList = "team"), @Index(columnList = "product"), @Index(columnList = "unit"), @Index(columnList = "meta_file"), @Index(columnList = "customer_referral"), @Index(columnList = "sale_order_line"), @Index(columnList = "invoice_line") })
@Track(fields = { @TrackField(name = "status"), @TrackField(name = "teamTaskCategory"), @TrackField(name = "progressSelect"), @TrackField(name = "assignedTo"), @TrackField(name = "invoicingType"), @TrackField(name = "customerReferral") }, messages = { @TrackMessage(message = "Task updated", condition = "true", on = TrackEvent.UPDATE) })
public class TeamTask extends AuditableModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEAM_TASK_SEQ")
	@SequenceGenerator(name = "TEAM_TASK_SEQ", sequenceName = "TEAM_TASK_SEQ", allocationSize = 1)
	private Long id;

	@NotNull
	private String name;

	@Widget(selection = "team.task.status")
	private String status;

	@Widget(selection = "team.task.priority")
	private String priority;

	private Integer sequence = 0;

	private LocalDate taskDate;

	private Integer taskDuration = 0;

	private LocalDate taskDeadline;

	private String relatedModel;

	private String relatedName;

	private Long relatedId = 0L;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User assignedTo;

	@Widget(title = "Frequency")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Frequency frequency;

	@Widget(title = "Next task", readonly = true)
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TeamTask nextTeamTask;

	@Widget(title = "First", readonly = true)
	private Boolean isFirst = Boolean.FALSE;

	@Widget(title = "Apply modifications to next tasks")
	private Boolean doApplyToAllNextTasks = Boolean.FALSE;

	@Widget(title = "Date or frequency changed", readonly = true)
	private Boolean hasDateOrFrequencyChanged = Boolean.FALSE;

	@Widget(title = "Type", selection = "team.task.type.select")
	private String typeSelect = "task";

	@Widget(title = "Project")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Project project;

	@Widget(title = "Name", search = { "id", "name" })
	@NameColumn
	private String fullName;

	@Widget(title = "Parent task")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TeamTask parentTask;

	@Widget(title = "Category")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private TeamTaskCategory teamTaskCategory;

	@Widget(title = "Progress", selection = "project.task.progress.select")
	private Integer progressSelect = 0;

	@Widget(title = "Team")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Team team;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<TeamTask> teamTaskList;

	@Widget(title = "Followers")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<User> membersUserSet;

	@Widget(title = "Description")
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "text")
	private String description;

	@Widget(title = "Product")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Product product;

	@Widget(title = "Unit")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Unit unit;

	@Widget(title = "Quantity")
	private BigDecimal quantity = BigDecimal.ZERO;

	@Widget(title = "Unit price")
	private BigDecimal unitPrice = BigDecimal.ZERO;

	@Widget(title = "Currency")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Currency currency;

	@Widget(title = "Planned progress")
	private BigDecimal plannedProgress = BigDecimal.ZERO;

	@Widget(title = "Predecessors tasks")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<TeamTask> finishToStartSet;

	@Widget(title = "Tasks to start before start")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<TeamTask> startToStartSet;

	@Widget(title = "Tasks to finish before finish")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<TeamTask> finishToFinishSet;

	@Widget(title = "Duration hours")
	private BigDecimal durationHours = BigDecimal.ZERO;

	@Widget(title = "Task end")
	private LocalDate taskEndDate;

	@OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private List<Timer> timerList;

	@Widget(title = "Estimated time")
	private BigDecimal budgetedTime = BigDecimal.ZERO;

	@Widget(title = "Signature")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private MetaFile metaFile;

	@Widget(title = "Tags")
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private Set<TeamTaskTag> teamTaskTagSet;

	@Widget(title = "Total planned hours")
	private BigDecimal totalPlannedHrs = BigDecimal.ZERO;

	@Widget(title = "Total real hours")
	private BigDecimal totalRealHrs = BigDecimal.ZERO;

	@Widget(title = "Project planning time lines")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "task", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProjectPlanningTime> projectPlanningTimeList;

	@Widget(title = "To invoice")
	private Boolean toInvoice = Boolean.FALSE;

	@Widget(title = "Invoiced")
	private Boolean invoiced = Boolean.FALSE;

	@Widget(title = "teamTask.exTaxTotal")
	private BigDecimal exTaxTotal = BigDecimal.ZERO;

	@Widget(title = "Discount type", selection = "base.price.list.line.amount.type.select")
	private Integer discountTypeSelect = 3;

	@Widget(title = "Discount amount")
	@Digits(integer = 10, fraction = 10)
	private BigDecimal discountAmount = BigDecimal.ZERO;

	@Widget(title = "Unit price discounted")
	@Digits(integer = 10, fraction = 10)
	private BigDecimal priceDiscounted = BigDecimal.ZERO;

	@Widget(title = "Invoicing Type", selection = "business.team.task.invoicing.type.select")
	private Integer invoicingType = 3;

	@Widget(title = "Paid")
	private Boolean isPaid = Boolean.FALSE;

	@Widget(title = "Customer referral")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private User customerReferral;

	@Widget(title = "Related sale order line")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private SaleOrderLine saleOrderLine;

	@Widget(title = "Related Invoice line")
	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	private InvoiceLine invoiceLine;

	@Widget(title = "Refused")
	private Boolean isTaskRefused = Boolean.FALSE;

	@Widget(title = "Attributes")
	@Basic(fetch = FetchType.LAZY)
	@Type(type = "json")
	private String attrs;

	public TeamTask() {
	}

	public TeamTask(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Integer getSequence() {
		return sequence == null ? 0 : sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public LocalDate getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(LocalDate taskDate) {
		this.taskDate = taskDate;
	}

	public Integer getTaskDuration() {
		return taskDuration == null ? 0 : taskDuration;
	}

	public void setTaskDuration(Integer taskDuration) {
		this.taskDuration = taskDuration;
	}

	public LocalDate getTaskDeadline() {
		return taskDeadline;
	}

	public void setTaskDeadline(LocalDate taskDeadline) {
		this.taskDeadline = taskDeadline;
	}

	public String getRelatedModel() {
		return relatedModel;
	}

	public void setRelatedModel(String relatedModel) {
		this.relatedModel = relatedModel;
	}

	public String getRelatedName() {
		return relatedName;
	}

	public void setRelatedName(String relatedName) {
		this.relatedName = relatedName;
	}

	public Long getRelatedId() {
		return relatedId == null ? 0L : relatedId;
	}

	public void setRelatedId(Long relatedId) {
		this.relatedId = relatedId;
	}

	public User getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(User assignedTo) {
		this.assignedTo = assignedTo;
	}

	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public TeamTask getNextTeamTask() {
		return nextTeamTask;
	}

	public void setNextTeamTask(TeamTask nextTeamTask) {
		this.nextTeamTask = nextTeamTask;
	}

	public Boolean getIsFirst() {
		return isFirst == null ? Boolean.FALSE : isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public Boolean getDoApplyToAllNextTasks() {
		return doApplyToAllNextTasks == null ? Boolean.FALSE : doApplyToAllNextTasks;
	}

	public void setDoApplyToAllNextTasks(Boolean doApplyToAllNextTasks) {
		this.doApplyToAllNextTasks = doApplyToAllNextTasks;
	}

	public Boolean getHasDateOrFrequencyChanged() {
		return hasDateOrFrequencyChanged == null ? Boolean.FALSE : hasDateOrFrequencyChanged;
	}

	public void setHasDateOrFrequencyChanged(Boolean hasDateOrFrequencyChanged) {
		this.hasDateOrFrequencyChanged = hasDateOrFrequencyChanged;
	}

	public String getTypeSelect() {
		return typeSelect;
	}

	public void setTypeSelect(String typeSelect) {
		this.typeSelect = typeSelect;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public TeamTask getParentTask() {
		return parentTask;
	}

	public void setParentTask(TeamTask parentTask) {
		this.parentTask = parentTask;
	}

	public TeamTaskCategory getTeamTaskCategory() {
		return teamTaskCategory;
	}

	public void setTeamTaskCategory(TeamTaskCategory teamTaskCategory) {
		this.teamTaskCategory = teamTaskCategory;
	}

	public Integer getProgressSelect() {
		return progressSelect == null ? 0 : progressSelect;
	}

	public void setProgressSelect(Integer progressSelect) {
		this.progressSelect = progressSelect;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<TeamTask> getTeamTaskList() {
		return teamTaskList;
	}

	public void setTeamTaskList(List<TeamTask> teamTaskList) {
		this.teamTaskList = teamTaskList;
	}

	/**
	 * Add the given {@link TeamTask} item to the {@code teamTaskList}.
	 *
	 * <p>
	 * It sets {@code item.parentTask = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addTeamTaskListItem(TeamTask item) {
		if (getTeamTaskList() == null) {
			setTeamTaskList(new ArrayList<>());
		}
		getTeamTaskList().add(item);
		item.setParentTask(this);
	}

	/**
	 * Remove the given {@link TeamTask} item from the {@code teamTaskList}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeTeamTaskListItem(TeamTask item) {
		if (getTeamTaskList() == null) {
			return;
		}
		getTeamTaskList().remove(item);
	}

	/**
	 * Clear the {@code teamTaskList} collection.
	 *
	 * <p>
	 * If you have to query {@link TeamTask} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearTeamTaskList() {
		if (getTeamTaskList() != null) {
			getTeamTaskList().clear();
		}
	}

	public Set<User> getMembersUserSet() {
		return membersUserSet;
	}

	public void setMembersUserSet(Set<User> membersUserSet) {
		this.membersUserSet = membersUserSet;
	}

	/**
	 * Add the given {@link User} item to the {@code membersUserSet}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addMembersUserSetItem(User item) {
		if (getMembersUserSet() == null) {
			setMembersUserSet(new HashSet<>());
		}
		getMembersUserSet().add(item);
	}

	/**
	 * Remove the given {@link User} item from the {@code membersUserSet}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeMembersUserSetItem(User item) {
		if (getMembersUserSet() == null) {
			return;
		}
		getMembersUserSet().remove(item);
	}

	/**
	 * Clear the {@code membersUserSet} collection.
	 *
	 */
	public void clearMembersUserSet() {
		if (getMembersUserSet() != null) {
			getMembersUserSet().clear();
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public BigDecimal getQuantity() {
		return quantity == null ? BigDecimal.ZERO : quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice == null ? BigDecimal.ZERO : unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getPlannedProgress() {
		return plannedProgress == null ? BigDecimal.ZERO : plannedProgress;
	}

	public void setPlannedProgress(BigDecimal plannedProgress) {
		this.plannedProgress = plannedProgress;
	}

	public Set<TeamTask> getFinishToStartSet() {
		return finishToStartSet;
	}

	public void setFinishToStartSet(Set<TeamTask> finishToStartSet) {
		this.finishToStartSet = finishToStartSet;
	}

	/**
	 * Add the given {@link TeamTask} item to the {@code finishToStartSet}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addFinishToStartSetItem(TeamTask item) {
		if (getFinishToStartSet() == null) {
			setFinishToStartSet(new HashSet<>());
		}
		getFinishToStartSet().add(item);
	}

	/**
	 * Remove the given {@link TeamTask} item from the {@code finishToStartSet}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeFinishToStartSetItem(TeamTask item) {
		if (getFinishToStartSet() == null) {
			return;
		}
		getFinishToStartSet().remove(item);
	}

	/**
	 * Clear the {@code finishToStartSet} collection.
	 *
	 */
	public void clearFinishToStartSet() {
		if (getFinishToStartSet() != null) {
			getFinishToStartSet().clear();
		}
	}

	public Set<TeamTask> getStartToStartSet() {
		return startToStartSet;
	}

	public void setStartToStartSet(Set<TeamTask> startToStartSet) {
		this.startToStartSet = startToStartSet;
	}

	/**
	 * Add the given {@link TeamTask} item to the {@code startToStartSet}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addStartToStartSetItem(TeamTask item) {
		if (getStartToStartSet() == null) {
			setStartToStartSet(new HashSet<>());
		}
		getStartToStartSet().add(item);
	}

	/**
	 * Remove the given {@link TeamTask} item from the {@code startToStartSet}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeStartToStartSetItem(TeamTask item) {
		if (getStartToStartSet() == null) {
			return;
		}
		getStartToStartSet().remove(item);
	}

	/**
	 * Clear the {@code startToStartSet} collection.
	 *
	 */
	public void clearStartToStartSet() {
		if (getStartToStartSet() != null) {
			getStartToStartSet().clear();
		}
	}

	public Set<TeamTask> getFinishToFinishSet() {
		return finishToFinishSet;
	}

	public void setFinishToFinishSet(Set<TeamTask> finishToFinishSet) {
		this.finishToFinishSet = finishToFinishSet;
	}

	/**
	 * Add the given {@link TeamTask} item to the {@code finishToFinishSet}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addFinishToFinishSetItem(TeamTask item) {
		if (getFinishToFinishSet() == null) {
			setFinishToFinishSet(new HashSet<>());
		}
		getFinishToFinishSet().add(item);
	}

	/**
	 * Remove the given {@link TeamTask} item from the {@code finishToFinishSet}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeFinishToFinishSetItem(TeamTask item) {
		if (getFinishToFinishSet() == null) {
			return;
		}
		getFinishToFinishSet().remove(item);
	}

	/**
	 * Clear the {@code finishToFinishSet} collection.
	 *
	 */
	public void clearFinishToFinishSet() {
		if (getFinishToFinishSet() != null) {
			getFinishToFinishSet().clear();
		}
	}

	public BigDecimal getDurationHours() {
		return durationHours == null ? BigDecimal.ZERO : durationHours;
	}

	public void setDurationHours(BigDecimal durationHours) {
		this.durationHours = durationHours;
	}

	public LocalDate getTaskEndDate() {
		return taskEndDate;
	}

	public void setTaskEndDate(LocalDate taskEndDate) {
		this.taskEndDate = taskEndDate;
	}

	public List<Timer> getTimerList() {
		return timerList;
	}

	public void setTimerList(List<Timer> timerList) {
		this.timerList = timerList;
	}

	/**
	 * Add the given {@link Timer} item to the {@code timerList}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addTimerListItem(Timer item) {
		if (getTimerList() == null) {
			setTimerList(new ArrayList<>());
		}
		getTimerList().add(item);
	}

	/**
	 * Remove the given {@link Timer} item from the {@code timerList}.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeTimerListItem(Timer item) {
		if (getTimerList() == null) {
			return;
		}
		getTimerList().remove(item);
	}

	/**
	 * Clear the {@code timerList} collection.
	 *
	 * <p>
	 * It sets {@code item.null = null} to break the relationship.
	 * </p>
	 */
	public void clearTimerList() {
		if (getTimerList() != null) {
			getTimerList().clear();
		}
	}

	public BigDecimal getBudgetedTime() {
		return budgetedTime == null ? BigDecimal.ZERO : budgetedTime;
	}

	public void setBudgetedTime(BigDecimal budgetedTime) {
		this.budgetedTime = budgetedTime;
	}

	public MetaFile getMetaFile() {
		return metaFile;
	}

	public void setMetaFile(MetaFile metaFile) {
		this.metaFile = metaFile;
	}

	public Set<TeamTaskTag> getTeamTaskTagSet() {
		return teamTaskTagSet;
	}

	public void setTeamTaskTagSet(Set<TeamTaskTag> teamTaskTagSet) {
		this.teamTaskTagSet = teamTaskTagSet;
	}

	/**
	 * Add the given {@link TeamTaskTag} item to the {@code teamTaskTagSet}.
	 *
	 * @param item
	 *            the item to add
	 */
	public void addTeamTaskTagSetItem(TeamTaskTag item) {
		if (getTeamTaskTagSet() == null) {
			setTeamTaskTagSet(new HashSet<>());
		}
		getTeamTaskTagSet().add(item);
	}

	/**
	 * Remove the given {@link TeamTaskTag} item from the {@code teamTaskTagSet}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeTeamTaskTagSetItem(TeamTaskTag item) {
		if (getTeamTaskTagSet() == null) {
			return;
		}
		getTeamTaskTagSet().remove(item);
	}

	/**
	 * Clear the {@code teamTaskTagSet} collection.
	 *
	 */
	public void clearTeamTaskTagSet() {
		if (getTeamTaskTagSet() != null) {
			getTeamTaskTagSet().clear();
		}
	}

	public BigDecimal getTotalPlannedHrs() {
		return totalPlannedHrs == null ? BigDecimal.ZERO : totalPlannedHrs;
	}

	public void setTotalPlannedHrs(BigDecimal totalPlannedHrs) {
		this.totalPlannedHrs = totalPlannedHrs;
	}

	public BigDecimal getTotalRealHrs() {
		return totalRealHrs == null ? BigDecimal.ZERO : totalRealHrs;
	}

	public void setTotalRealHrs(BigDecimal totalRealHrs) {
		this.totalRealHrs = totalRealHrs;
	}

	public List<ProjectPlanningTime> getProjectPlanningTimeList() {
		return projectPlanningTimeList;
	}

	public void setProjectPlanningTimeList(List<ProjectPlanningTime> projectPlanningTimeList) {
		this.projectPlanningTimeList = projectPlanningTimeList;
	}

	/**
	 * Add the given {@link ProjectPlanningTime} item to the {@code projectPlanningTimeList}.
	 *
	 * <p>
	 * It sets {@code item.task = this} to ensure the proper relationship.
	 * </p>
	 *
	 * @param item
	 *            the item to add
	 */
	public void addProjectPlanningTimeListItem(ProjectPlanningTime item) {
		if (getProjectPlanningTimeList() == null) {
			setProjectPlanningTimeList(new ArrayList<>());
		}
		getProjectPlanningTimeList().add(item);
		item.setTask(this);
	}

	/**
	 * Remove the given {@link ProjectPlanningTime} item from the {@code projectPlanningTimeList}.
	 *
 	 * @param item
	 *            the item to remove
	 */
	public void removeProjectPlanningTimeListItem(ProjectPlanningTime item) {
		if (getProjectPlanningTimeList() == null) {
			return;
		}
		getProjectPlanningTimeList().remove(item);
	}

	/**
	 * Clear the {@code projectPlanningTimeList} collection.
	 *
	 * <p>
	 * If you have to query {@link ProjectPlanningTime} records in same transaction, make
	 * sure to call {@link javax.persistence.EntityManager#flush() } to avoid
	 * unexpected errors.
	 * </p>
	 */
	public void clearProjectPlanningTimeList() {
		if (getProjectPlanningTimeList() != null) {
			getProjectPlanningTimeList().clear();
		}
	}

	public Boolean getToInvoice() {
		return toInvoice == null ? Boolean.FALSE : toInvoice;
	}

	public void setToInvoice(Boolean toInvoice) {
		this.toInvoice = toInvoice;
	}

	public Boolean getInvoiced() {
		return invoiced == null ? Boolean.FALSE : invoiced;
	}

	public void setInvoiced(Boolean invoiced) {
		this.invoiced = invoiced;
	}

	public BigDecimal getExTaxTotal() {
		return exTaxTotal == null ? BigDecimal.ZERO : exTaxTotal;
	}

	public void setExTaxTotal(BigDecimal exTaxTotal) {
		this.exTaxTotal = exTaxTotal;
	}

	public Integer getDiscountTypeSelect() {
		return discountTypeSelect == null ? 0 : discountTypeSelect;
	}

	public void setDiscountTypeSelect(Integer discountTypeSelect) {
		this.discountTypeSelect = discountTypeSelect;
	}

	public BigDecimal getDiscountAmount() {
		return discountAmount == null ? BigDecimal.ZERO : discountAmount;
	}

	public void setDiscountAmount(BigDecimal discountAmount) {
		this.discountAmount = discountAmount;
	}

	public BigDecimal getPriceDiscounted() {
		return priceDiscounted == null ? BigDecimal.ZERO : priceDiscounted;
	}

	public void setPriceDiscounted(BigDecimal priceDiscounted) {
		this.priceDiscounted = priceDiscounted;
	}

	public Integer getInvoicingType() {
		return invoicingType == null ? 0 : invoicingType;
	}

	public void setInvoicingType(Integer invoicingType) {
		this.invoicingType = invoicingType;
	}

	public Boolean getIsPaid() {
		return isPaid == null ? Boolean.FALSE : isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
	}

	public User getCustomerReferral() {
		return customerReferral;
	}

	public void setCustomerReferral(User customerReferral) {
		this.customerReferral = customerReferral;
	}

	public SaleOrderLine getSaleOrderLine() {
		return saleOrderLine;
	}

	public void setSaleOrderLine(SaleOrderLine saleOrderLine) {
		this.saleOrderLine = saleOrderLine;
	}

	public InvoiceLine getInvoiceLine() {
		return invoiceLine;
	}

	public void setInvoiceLine(InvoiceLine invoiceLine) {
		this.invoiceLine = invoiceLine;
	}

	public Boolean getIsTaskRefused() {
		return isTaskRefused == null ? Boolean.FALSE : isTaskRefused;
	}

	public void setIsTaskRefused(Boolean isTaskRefused) {
		this.isTaskRefused = isTaskRefused;
	}

	public String getAttrs() {
		return attrs;
	}

	public void setAttrs(String attrs) {
		this.attrs = attrs;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (this == obj) return true;
		if (!(obj instanceof TeamTask)) return false;

		final TeamTask other = (TeamTask) obj;
		if (this.getId() != null || other.getId() != null) {
			return Objects.equals(this.getId(), other.getId());
		}

		return false;
	}

	@Override
	public int hashCode() {
		return 31;
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
			.add("id", getId())
			.add("name", getName())
			.add("status", getStatus())
			.add("priority", getPriority())
			.add("sequence", getSequence())
			.add("taskDate", getTaskDate())
			.add("taskDuration", getTaskDuration())
			.add("taskDeadline", getTaskDeadline())
			.add("relatedModel", getRelatedModel())
			.add("relatedName", getRelatedName())
			.add("relatedId", getRelatedId())
			.add("isFirst", getIsFirst())
			.omitNullValues()
			.toString();
	}
}
