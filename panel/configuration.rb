class Configuration
  attr_accessor :fault_tolerance_level, :method

  def initialize(fault_tolerance_level:, method:)
    @fault_tolerance_level = fault_tolerance_level
    @method = method
  end
end
