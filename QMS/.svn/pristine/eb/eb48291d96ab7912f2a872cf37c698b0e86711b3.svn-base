<template>
	<!--
    	作者：offline
    	时间：2018-03-29
    	描述：上传视频
    -->
    <div class="upload">
    		<div>
    			<div>{{$t('lessons.title')}}：</div>
    			<div style="width: 40%;">
    				<el-input></el-input>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;">
    			<div>{{$t('lessons.describe')}}：</div>
    			<div style="width: 40%;">
    				<el-input type="textarea" :autosize="{ minRows: 3}">
					</el-input>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;">
    			<div>{{$t('lessons.CoverPicture')}}：</div>
    			<div>
    				<el-button style="width: 150px;height: 110px;">
    					<Icon type="upload" style="font-size: 50px;"></Icon>
    					<p>{{$t('lessons.ClickToUploadTheCover')}}</p>
    				</el-button>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;">
    			<div>{{$t('lessons.VideoFile')}}：</div>
    			<div>
    				<el-button style="width: 150px;height: 110px;">
    					<Icon type="ios-videocam" style="font-size: 50px;"></Icon>
    					<p>{{$t('lessons.ClickUploadVideo')}}</p>
    				</el-button>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;">
    			<div>{{$t('lessons.DisplayPriority')}}：</div>
    			<div style="width: 40%;">
    				<el-input></el-input>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;">
    			<div>{{$t('lessons.VisualUser')}}：</div>
    			<div style="width: 20%;padding-left: 2%;padding-top: 20px;">

    				<el-checkbox-group v-model="checkList">

    					<el-checkbox :label="$t('lessons.ExternalUser')" style="display: block;height: 40px;margin-left: 30px;"></el-checkbox>
    					<el-checkbox :label="$t('lessons.InternalUser')" style="display: block;height: 40px;"></el-checkbox>
    					<el-checkbox :label="$t('lessons.PublicClient')" style="display: block;height: 40px;"></el-checkbox>
    					<el-checkbox :label="$t('lessons.client')" style="display: block;height: 40px;"></el-checkbox>
  					</el-checkbox-group>
    			</div>
    		</div>
    		
    		<div style="margin-top: 50px;padding-left: 5%;">
    			<el-button type="primary" style="width: 150px;">{{$t('lessons.new')}}</el-button>
    		</div>
    </div>
</template>

<script>
	export default {
    data () {
      return {
        checkList: [],
      };
    }
  };
</script>

<style>
	.upload{
		background-color: white;margin-bottom:200px;padding-left: 10%;padding-top: 30px;padding-bottom: 100px;
	}
</style>