<template>
	<div class="layout-content">
		<el-card shadow="hover">
			<el-row>
 			<el-col :span="24">
 			 	<div class="createtitle"><i class="icon iconfont">&#xe608;</i>
 			 		风险等级分为5级，1级（0~40分）/2级（40~55分）/3级（55~70分）/4级（70~85分）/5级（85~100分）							
				</div>
			</el-col>
		</el-row>
		<el-table
			:data="InspectTable1"
			class="InspectTable">
			<el-table-column
		      prop="bsRiskLevel"
		      label="风险等级"
		      width="100"
		      align="center"		      
		      >
    		</el-table-column>
    		<el-table-column
		      prop="date"
		      label="Verification levels 检验水平 (VL)" align="center"
		      >
		      	<el-table-column
			      prop="bsLevel0"
			      label="R"
			      align="center"			     
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel1"
			      label="I"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel2"
			      label="II"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel3"
			      label="III"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel4"
			      label="IV"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel5"
			      label="V"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel6"
			      label="VI"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel7"
			      label="VII"
			      align="center"
			      >
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel8"
			      label="T"
			      align="center"
			      >
	    		</el-table-column>
	    		 		
    		</el-table-column>
    		 <el-table-column
    		 align="center"
		      fixed="right"
		      label="操作"
		      width="100">
		      <template slot-scope="scope">
		        <el-button @click="showEditDialog(scope.row)" type="text" size="small">编辑</el-button>		        
		      </template>
  			</el-table-column>
		</el-table>	
		<div class="DescInfo">
			<p><i class="icon iconfont">&#xe608;</i>说明: 												
			1.定义抽样标准为：MIL-STD-1916，正常IV级，AC=0..												
			2.当风险等级为1和2,采取III减量检验水平,当风险等级为3等级, 采取IV正常检验水平.当风险等级为4和5, 采取V加严检验水平.												
			3.正常VL值左/右边的那个值分别是加严/减量检验计划, 例如:VL值VII的加严检验计划是T,VL值I的减量计划是R. 												
			</p>
		</div>
		</el-card>
		<el-card shadow="never" class="InspectTable">		
		<el-table
			:data="InspectTable2"
			>
			<el-table-column
		      prop=""
		      :label="$t('sample.batch')"
		      width="100"
		      align="center"
		      >
		      <el-table-column
			      prop="bsLowerLimit"   
			      :label="$t('sample.lowLimit')"
			      align="center"
			      >			  
	    		</el-table-column>
		      	<el-table-column
			      prop="bsUpperLimit"  
			      label="上限"
			      align="center"
			      >			    
	    		</el-table-column>
	    		
    		</el-table-column>
    		<el-table-column
		      prop="date"
		      label="验证水平" align="center"
		      >
		      	<el-table-column
			      prop="bsLevel7"
			      label="VII"
			      align="center"
			      >
			    <template scope="scope">        
			        <span v-if='scope.row.bsLevel7===1'>A</span>
			        <span v-if='scope.row.bsLevel7===2'>B</span>
			        <span v-if='scope.row.bsLevel7===3'>C</span>
			        <span v-if='scope.row.bsLevel7===4'>D</span>
			        <span v-if='scope.row.bsLevel7===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel6"
			      label="VI"
			      align="center"
			      >
			     <template scope="scope">        
			        <span v-if='scope.row.bsLevel6===1'>A</span>
			        <span v-if='scope.row.bsLevel6===2'>B</span>
			        <span v-if='scope.row.bsLevel6===3'>C</span>
			        <span v-if='scope.row.bsLevel6===4'>D</span>
			        <span v-if='scope.row.bsLevel6===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel5"
			      label="V"
			      align="center"
			      >
			      <template scope="scope">        
			        <span v-if='scope.row.bsLevel5===1'>A</span>
			        <span v-if='scope.row.bsLevel5===2'>B</span>
			        <span v-if='scope.row.bsLevel5===3'>C</span>
			        <span v-if='scope.row.bsLevel5===4'>D</span>
			        <span v-if='scope.row.bsLevel5===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel4"
			      label="IV"
			      align="center"
			      >
			      <template scope="scope">        
			        <span v-if='scope.row.bsLevel4===1'>A</span>
			        <span v-if='scope.row.bsLevel4===2'>B</span>
			        <span v-if='scope.row.bsLevel4===3'>C</span>
			        <span v-if='scope.row.bsLevel4===4'>D</span>
			        <span v-if='scope.row.bsLevel4===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel3"
			      label="III"
			      align="center"
			      >
			      <template scope="scope">        
			        <span v-if='scope.row.bsLevel3===1'>A</span>
			        <span v-if='scope.row.bsLevel3===2'>B</span>
			        <span v-if='scope.row.bsLevel3===3'>C</span>
			        <span v-if='scope.row.bsLevel3===4'>D</span>
			        <span v-if='scope.row.bsLevel3===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel2"
			      label="II"
			      align="center"
			      >
			      <template scope="scope">        
			        <span v-if='scope.row.bsLevel2===1'>A</span>
			        <span v-if='scope.row.bsLevel2===2'>B</span>
			        <span v-if='scope.row.bsLevel2===3'>C</span>
			        <span v-if='scope.row.bsLevel2===4'>D</span>
			        <span v-if='scope.row.bsLevel2===5'>E</span>
   				</template>
	    		</el-table-column>
	    		<el-table-column
			      prop="bsLevel1"
			      label="I"
			      align="center"
			      >
			      <template scope="scope">        
			        <span v-if='scope.row.bsLevel1===1'>A</span>
			        <span v-if='scope.row.bsLevel1===2'>B</span>
			        <span v-if='scope.row.bsLevel1===3'>C</span>
			        <span v-if='scope.row.bsLevel1===4'>D</span>
			        <span v-if='scope.row.bsLevel1===5'>E</span>
   				</template>
	    		</el-table-column>
    		</el-table-column>
    		 <el-table-column
    		 align="center"
		      fixed="right"
		      label="操作"
		      width="100">
		      <template slot-scope="scope">
		        <el-button @click="showEditDialog2(scope.row)" type="text" size="small">编辑</el-button>	   
		      </template>
  			</el-table-column>
		</el-table>	
		<div class="DescInfo">
			<p style="color:red"><i class="icon iconfont">&#xe608;</i>											
			备注: Ac=0 即, 0收1退											
			</p>
		</div>


			<el-table
			:data="InspectTable3"
			border
			style="width: 100%">
			<el-table-column
			fixed
			  prop="bsCode"
			  label="样本代字"
			  align="center"
			  >
			</el-table-column>
			<el-table-column
			  prop="bsLevel8"
			  label="T"
			  align="center"
			 >			 
			</el-table-column>
			<el-table-column
			  prop="bsLevel7"
			  label="VII"
			  align="center"
			>			 
			</el-table-column>
			<el-table-column
			  prop="bsLevel6"
			  align="center"
			  label="VI">
			</el-table-column>
			<el-table-column
			  prop="bsLevel5"
			  align="center"
			  label="V">
			</el-table-column>
			<el-table-column
			  prop="bsLevel4"
			  align="center"
			  label="IV">
			</el-table-column>
			<el-table-column
			  prop="bsLevel3"
			  align="center"
			  label="III">
			</el-table-column>
			<el-table-column
			  prop="bsLevel2"
			  align="center"
			  label="II">
			</el-table-column>
			<el-table-column
			  prop="bsLevel1"
			  align="center"
			  label="I">
			</el-table-column>
			<el-table-column
			  prop="bsLevel0"
			  align="center"
			  label="R">
			</el-table-column>
			 <el-table-column
    		 align="center"
		      fixed="right"
		      label="操作"
		      width="100">
		      <template slot-scope="scope">
		        <el-button @click="showEditDialog3(scope.row)" type="text" size="small">编辑</el-button>	   
		      </template>
  			</el-table-column>
			</el-table>
		<div class="DescInfo">
			<p style="color:red"><i class="icon iconfont">&#xe608;</i>											
			1、当批量比样本量小则100%检验<br>
			<i style="margin-left:20px"></i>2、加严检验在正常检验VL 左边之隔栏，减量检验则为右边之隔栏。										
			</p>
		</div>
		</el-card>
<!--   -->
   		<Modal :inline="true" v-model="modal_dialog" title="Verification levels 检验水平 (VL)" @on-ok="saveHandler" width="45%">
            <div class="InspectItemModel">
                <el-form label-width="70px" v-model="InspectItem"  :inline="true">               
                    <el-form-item :label="$t('sample.VL[0]')"prop="bsLevel0">
					<el-select  v-model="InspectItem.bsLevel0" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
					</el-form-item>

                    <el-form-item :label="$t('sample.VL[1]')" prop="bsLevel1">                       
                        <el-select  v-model="InspectItem.bsLevel1" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>               
               
                    <el-form-item :label="$t('sample.VL[2]')" prop="bsLevel2">
                        <el-select  v-model="InspectItem.bsLevel2" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>               
               
                    <el-form-item :label="$t('sample.VL[3]')" prop="bsLevel3">
                        <el-select  v-model="InspectItem.bsLevel3" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                     <el-form-item :label="$t('sample.VL[4]')" prop="bsLevel4">
                        <el-select  v-model="InspectItem.bsLevel4" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[5]')" prop="bsLevel5">
                        <el-select  v-model="InspectItem.bsLevel5" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[6]')" prop="bsLevel6">
                       <el-select  v-model="InspectItem.bsLevel6" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[7]')" prop="bsLevel7">
                        <el-select  v-model="InspectItem.bsLevel7" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[8]')" prop="bsLevel8">
                      <el-select  v-model="InspectItem.bsLevel8" placeholder="请选择">
						<el-option v-for="item in Options" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>                   
                    
                </el-form>
            </div>
        </Modal>
<!-- model2	 -->
	<Modal :inline="true" v-model="modal_dialog2" title="检验水平 (VL)" @on-ok="saveHandler2" width="45%">
            <div class="InspectItemModel">
                <el-form label-width="70px" v-model="InspectItem2"  :inline="true">
                <el-col :span="12">              
                    <el-form-item :label="$t('sample.Low')"prop="bsLevel0">                    	
						 <el-input v-model="InspectItem2.bsLowerLimit"></el-input>	
					</el-form-item>
				</el-col>
				<el-col :span="12"> 
					<el-form-item :label="$t('sample.Up')"prop="bsLevel0">						
						 <el-input v-model="InspectItem2.bsUpperLimit"></el-input>					
					</el-form-item>
				</el-col>
                    <el-form-item :label="$t('sample.VL[1]')" prop="bsLevel1">                       
                        <el-select  v-model="InspectItem2.bsLevel1" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>               
               
                    <el-form-item :label="$t('sample.VL[2]')" prop="bsLevel2">
                        <el-select  v-model="InspectItem2.bsLevel2" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>               
               
                    <el-form-item :label="$t('sample.VL[3]')" prop="bsLevel3">
                        <el-select  v-model="InspectItem2.bsLevel3" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                     <el-form-item :label="$t('sample.VL[4]')" prop="bsLevel4">
                        <el-select  v-model="InspectItem2.bsLevel4" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[5]')" prop="bsLevel5">
                        <el-select  v-model="InspectItem2.bsLevel5" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[6]')" prop="bsLevel6">
                       <el-select  v-model="InspectItem2.bsLevel6" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>
                    <el-form-item :label="$t('sample.VL[7]')" prop="bsLevel7">
                        <el-select  v-model="InspectItem2.bsLevel7" placeholder="请选择">
						<el-option v-for="item in Options2" :key="item.value" :label="item.label" :value="item.value" >							
						</el-option>
					</el-select>
                    </el-form-item>                                    
                    
                </el-form>
            </div>
        </Modal>
<!--  modal3	 -->
		<Modal :inline="true" v-model="modal_dialog3" title="样本代字" @on-ok="saveHandler3" width="45%">
    <div class="InspectItemModel">
        <el-form label-width="70px" v-model="InspectItem3"  :inline="true">               
            <el-form-item :label="$t('sample.VL[0]')"prop="bsLevel0">
			<el-input v-model="InspectItem3.bsLevel0" ></el-input>
			</el-form-item>

            <el-form-item :label="$t('sample.VL[1]')" prop="bsLevel1">                       
               <el-input v-model="InspectItem3.bsLevel1" ></el-input>
            </el-form-item>               
       
            <el-form-item :label="$t('sample.VL[2]')" prop="bsLevel2">
               <el-input v-model="InspectItem3.bsLevel2" ></el-input>
            </el-form-item>               
       
            <el-form-item :label="$t('sample.VL[3]')" prop="bsLevel3">
                <el-input v-model="InspectItem3.bsLevel3"></el-input>
            </el-form-item>
             <el-form-item :label="$t('sample.VL[4]')" prop="bsLevel4">
                <el-input v-model="InspectItem3.bsLevel4" ></el-input>
            </el-form-item>
            <el-form-item :label="$t('sample.VL[5]')" prop="bsLevel5">
               <el-input v-model="InspectItem3.bsLevel5"></el-input>
            </el-form-item>
            <el-form-item :label="$t('sample.VL[6]')" prop="bsLevel6">
              <el-input v-model="InspectItem3.bsLevel6"></el-input>
            </el-form-item>
            <el-form-item :label="$t('sample.VL[7]')" prop="bsLevel7">
                 <el-input v-model="InspectItem3.bsLevel7"></el-input>
            </el-form-item>
            <el-form-item :label="$t('sample.VL[8]')" prop="bsLevel8">
              <el-input v-model="InspectItem3.bsLevel8"></el-input>
            </el-form-item>                 
            
        </el-form>
    </div>
</Modal>

	</div>
</template>
<script>
	export default {
		data() {
			return {
				InspectTable1:[],
				InspectTable2:[],
				InspectTable3:[],
				modal_dialog:false,
				modal_dialog2:false,
				modal_dialog3:false,
				InspectItem:{},	
				InspectItem2:{},
				InspectItem3:{},
				Options:[
            	{label:this.$t("sample.VlValue[0]"),value:0},
				{label:this.$t("sample.VlValue[1]"),value:1}				     			
            	],
            	Options2:[
            	{label:this.$t("sample.VlValue2[0]"),value:1},
				{label:this.$t("sample.VlValue2[1]"),value:2},	
				{label:this.$t("sample.VlValue2[2]"),value:3},
				{label:this.$t("sample.VlValue2[3]"),value:4},
				{label:this.$t("sample.VlValue2[4]"),value:5},			     			
            	]


			}
		},
		created() {
			this.getData();
		},
		methods:{
				getData() {
					this.api.sampleLevelRisk.getlist().then((res) => {									
                		this.InspectTable1 = res.data.rows;
               			
            		});

            		this.api.sampleLevelTotalCode.getlist().then((res) => {             		 
            			for (var i = 0; i < res.data.rows.length; i++) {
	                    	var row = res.data.rows[i];
	                    if (row.bsUpperLimit == 0) 
	                    	row.bsUpperLimit ='-';	                    	

                	 	}    			         							
                		this.InspectTable2 = res.data.rows;
               			
            		});

            		this.api.sampleLevelCodeNum.getlist().then((res) => {      							
                		this.InspectTable3 = res.data.rows;
               			
            		});


				},
			showEditDialog(row){
		 		this.modal_dialog=true;		 	
		 		this.InspectItem = Object.assign({},row);
			},
			showEditDialog2(row){
		 		this.modal_dialog2=true;		 	
		 		this.InspectItem2 = Object.assign({},row);
			},
			showEditDialog3(row){
		 		this.modal_dialog3=true;		 	
		 		this.InspectItem3 = Object.assign({},row);
			},
			saveHandler(){
				var form={
					id:this.InspectItem.id,
					bsRiskLevel:this.InspectItem.bsRiskLevel,
					bsLevel0:this.InspectItem.bsLevel0,
					bsLevel1:this.InspectItem.bsLevel1,
					bsLevel2:this.InspectItem.bsLevel2,
					bsLevel3:this.InspectItem.bsLevel3,
					bsLevel4:this.InspectItem.bsLevel4,
					bsLevel5:this.InspectItem.bsLevel5,
					bsLevel6:this.InspectItem.bsLevel6,
					bsLevel7:this.InspectItem.bsLevel7,
					bsLevel8:this.InspectItem.bsLevel8,

				}
				this.api.sampleLevelRisk.edit(form).then((res) => {	
				if(res.result){
					this.getData();
					}	 		
	
            	});
			},
			saveHandler2(){

				var form={
					id:this.InspectItem2.id,
					bsLowerLimit:this.InspectItem2.bsLowerLimit,	
					bsUpperLimit:this.InspectItem2.bsUpperLimit,
					bsLevel1:this.InspectItem2.bsLevel1,
					bsLevel2:this.InspectItem2.bsLevel2,
					bsLevel3:this.InspectItem2.bsLevel3,
					bsLevel4:this.InspectItem2.bsLevel4,
					bsLevel5:this.InspectItem2.bsLevel5,
					bsLevel6:this.InspectItem2.bsLevel6,
					bsLevel7:this.InspectItem2.bsLevel7,

				}
				this.api.sampleLevelTotalCode.edit(form).then((res) => {	
				if(res.result){					
					this.getData();
					}	 		
	
            	});
			},
			saveHandler3(){
				var form={
					id:this.InspectItem3.id,
					bsLevel0:this.InspectItem3.bsLevel0,
					bsLevel1:this.InspectItem3.bsLevel1,
					bsLevel2:this.InspectItem3.bsLevel2,
					bsLevel3:this.InspectItem3.bsLevel3,
					bsLevel4:this.InspectItem3.bsLevel4,
					bsLevel5:this.InspectItem3.bsLevel5,
					bsLevel6:this.InspectItem3.bsLevel6,
					bsLevel7:this.InspectItem3.bsLevel7,	
					bsLevel8:this.InspectItem3.bsLevel8,		


				}
				this.api.sampleLevelCodeNum.edit(form).then((res) => {	
				if(res.result){					
					this.getData();
					}	 		
	
            	});
			}
		}
	}

</script>
<style scoped>
	
	.createtitle {
    padding-left: 1%;
    height: 50px;
    font-size: 14px;
    font-family: "Microsoft YaHei";
    background: #f2f7fb;
    line-height: 50px;
    color:#777;
}
.InspectTable{margin-top:10px;}
.createtitle .icon{font-size: 25px;color:blue;}
.DescInfo{margin-top:10px; font-family: "Microsoft YaHei"}
.InspectItemModel{width:100%;}
</style>