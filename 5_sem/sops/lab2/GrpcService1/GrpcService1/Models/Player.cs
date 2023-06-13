using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;
using System.Linq;
using System.Threading.Tasks;

namespace GrpcService1.Models
{
    public class Player
    {
		[Key]
		public string playerid { get; set; }
		public int jersey { get; set; }
		public string fname { get; set; }
		public string sname { get; set; }
		public string position { get; set; }

		[DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
		[DataType(DataType.Date)]
		public DateTime birthday { get; set; }
		public int weight { get; set; }
		public int height { get; set; }
		public string birthcity { get; set; }
		public string birthstate { get; set; }
	}
}
